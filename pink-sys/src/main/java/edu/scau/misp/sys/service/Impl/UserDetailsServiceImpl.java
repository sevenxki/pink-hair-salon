package edu.scau.misp.sys.service.Impl;



import edu.scau.misp.hs.entity.*;
import edu.scau.misp.hs.mapper.*;
import edu.scau.misp.sys.domain.HsLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Authentication用户认证服务类
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    HsUserMapper hsUserMapper;
    @Autowired
    HsRoleMapper hsRoleMapper;
    @Autowired
    HsMenuMapper hsMenuMapper;
    @Autowired
    HsUserRoleMapper hsUserRoleMapper;
    @Autowired
    HsRoleMenuMapper hsRoleMenuMapper;


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            List<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority authority;
//            System.out.println("用了");
            HsUser hsUser = this.hsUserMapper.queryByUsername(username);
//            System.out.println("用了"+hsUser.getPassword());
            // 是否找到用户，未找到抛出异常
            if (hsUser == null) {
                throw new UsernameNotFoundException("未找到用户");
            }
            HsUserRole hsUserRole = new HsUserRole();
            hsUserRole.setUserId(hsUser.getUserId());
            List<HsUserRole> hsUserRoles = this.hsUserRoleMapper.queryAll(hsUserRole);
            List<HsRole> hsRoles = new ArrayList<>();

            for(HsUserRole hsUserRole1:hsUserRoles){
                HsRole hsRole = this.hsRoleMapper.queryByIdAndAva(hsUserRole1.getRoleId());
//                System.out.println("getRoleStatus"+hsRole.getRoleStatus());
                if(hsRole.getRoleStatus() == 1) {
                    hsRoles.add(hsRole);
                }
            }
//            System.out.println("hsRoles"+hsRoles);

            // 是否获得权限集合
            if(!hsRoles.isEmpty()){
//                System.out.println("到了这边2");
//                System.out.println(hsRoles != null);
                for(HsRole hsRole : hsRoles){
//                    System.out.println("到了这边3");
                    //更改为String类型
                    String a =String.valueOf(hsRole.getRoleName());
                    authority = new SimpleGrantedAuthority(a);
                    authorities.add(authority);
                }
            }

//            List<Integer> menuIds = new ArrayList<>();
//
//            List<HsRoleMenu> hsRoleMenus = new ArrayList<>();
//            for(HsUserRole hsUserRole1:hsUserRoles){
//                Integer roleId = hsUserRole1.getRoleId();
//                HsRoleMenu hsRoleMenu = new HsRoleMenu();
//                hsRoleMenu.setRoleId(roleId);
//                List<HsRoleMenu> hsRoleMenuList = this.hsRoleMenuMapper.queryAll(hsRoleMenu);
//                for(HsRoleMenu hsRoleMenu1:hsRoleMenuList){
//                    hsRoleMenus.add(hsRoleMenu1);
//                }
//            }
//            for(HsRoleMenu hsRoleMenu:hsRoleMenus){
//                Integer m = hsRoleMenu.getMenuId();
//                boolean a = false;
//                for(Integer id:menuIds){
//                    if(m == id){
//                        a = true;
//                    }
//                }
//                if (a == false){
//                    menuIds.add(m);
//                }
//            }
//            List<HsMenu> hsMenus = new ArrayList<>();
//            for(Integer id:menuIds){
//                HsMenu hsMenu = new HsMenu();
//                hsMenu = this.hsMenuMapper.queryById(id);
//                hsMenus.add(hsMenu);
//            }


//            单个对应类型
//            HsRole hsRole = new HsRole();
//            hsRole.setRoleId(this.hsUserRoleService.queryByUserId(hsUserRole.getUserId()).getRoleId());
//            List<HsRole> roles = this.hsRoleService.queryAll(hsRole);

//            // 是否获得权限集合
//            if(hsMenus != null){
//                for(HsMenu hsMenu : hsMenus){
//                    //更改为String类型
//                    String a =String.valueOf(hsMenu.getPath());
//                    authority = new SimpleGrantedAuthority(a);
//                    authorities.add(authority);
//                }
//            }

             //创建登录用户对象进行验证
            return new HsLoginUser(hsUser.getUserId(),hsUser.getUsername(), hsUser.getPassword(), authorities);


//            String password = "$2a$10$h9nAZUoc7z48B51qzUzFiOCWq513qYVnvDcdqSdrPr0z4YSBNfZEu"; // 明文密码为123456
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            GrantedAuthority authority = new SimpleGrantedAuthority("admin");
//            authorities.add(authority);
//            return new HsLoginUser(1,"admin", password, authorities);

        }
    }