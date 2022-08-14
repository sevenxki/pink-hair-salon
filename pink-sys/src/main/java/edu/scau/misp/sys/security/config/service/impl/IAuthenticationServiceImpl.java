package edu.scau.misp.sys.security.config.service.impl;



import edu.scau.misp.hs.entity.HsRole;
import edu.scau.misp.hs.entity.HsStaff;
import edu.scau.misp.hs.entity.HsUser;
import edu.scau.misp.hs.entity.HsUserRole;
import edu.scau.misp.hs.mapper.HsRoleMapper;
import edu.scau.misp.hs.mapper.HsStaffMapper;
import edu.scau.misp.hs.mapper.HsUserRoleMapper;
import edu.scau.misp.sys.domain.HsLoginUser;
import edu.scau.misp.hs.service.HsMenuService;

import edu.scau.misp.sys.security.config.service.HsUserService;
import edu.scau.misp.sys.security.config.service.IAuthenticationService;

import edu.scau.misp.sys.service.HsTokenService;
import edu.scau.misp.sys.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IAuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    HsUserService hsuserService;

    @Autowired
    HsMenuService hsMenuService;

    @Autowired
    HsUserService hsUserService;

    @Autowired
    private HsTokenService tokenService;

    @Autowired
    private HsUserRoleMapper hsUserRoleMapper;

    @Autowired
    private HsRoleMapper hsRoleMapper;

    @Autowired
    private HsStaffMapper hsStaffMapper;

    @Override
    public String login(String username, String password) throws AuthenticationException {
//        System.out.println(username+password);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HsLoginUser loginUser = (HsLoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 获得登录用户信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Map<String, Object> getUserInfo() throws AuthenticationException {
        HashMap<String,Object> userInfo = new HashMap<>();
        // 注意以下这句需要在LoginUser类中添加SysUser属性
        // 在UserDetailsServiceImpl创建LoginUser对象时，将sysUser设置进去。
        // 比较简单，此处就不贴出LoginUser代码和UserDetailsServiceImpl代码
        // 目的是：方便通过loginUser直接拿到sysUser。这样有利于笔记3实现权限。
        HsUser hsUser = new HsUser();
//        System.out.println(SecurityUtils.getLoginUser());
        hsUser.setUserId(SecurityUtils.getLoginUser().getUserId());
        hsUser.setUsername(SecurityUtils.getLoginUser().getUsername());
        HsUser hsUser1 = this.hsUserService.queryById(hsUser.getUserId());
        HsUserRole hsUserRole = new HsUserRole();
        hsUserRole.setUserId(hsUser.getUserId());
        List<HsUserRole> hsUserRoleList = this.hsUserRoleMapper.queryAll(hsUserRole);
        List<HsRole> hsRoles = new ArrayList<>();
        for(HsUserRole hsUserRole1:hsUserRoleList){
            HsRole hsRole = this.hsRoleMapper.queryById(hsUserRole1.getRoleId());
            hsRoles.add(hsRole);
        }
        String[] a = new String[1];
        a[0] = hsRoles.get(0).getRoleName();
//        hsUser.setUsername(SecurityUtils.getLoginUser().getUsername());
//        hsUser.setPassword(SecurityUtils.getLoginUser().getPassword());

        HsStaff hsStaff = new HsStaff();
        hsStaff = this.hsStaffMapper.queryByUsername(hsUser.getUsername());

        userInfo.put("roles",a);
        userInfo.put("name",hsUser.getUsername());
        userInfo.put("avatar",hsUser1.getAvatar());
        userInfo.put("staffName",hsStaff.getStaffName());
        return userInfo;
    }

}

