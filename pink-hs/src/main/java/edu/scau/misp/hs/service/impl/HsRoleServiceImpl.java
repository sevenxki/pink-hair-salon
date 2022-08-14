package edu.scau.misp.hs.service.impl;



import edu.scau.misp.hs.entity.HsRole;
import edu.scau.misp.hs.entity.HsStaff;
import edu.scau.misp.hs.entity.HsUser;
import edu.scau.misp.hs.entity.HsUserRole;
import edu.scau.misp.hs.mapper.HsStaffMapper;
import edu.scau.misp.hs.mapper.HsUserMapper;
import edu.scau.misp.hs.mapper.HsUserRoleMapper;
import edu.scau.misp.hs.service.HsRoleService;
import edu.scau.misp.hs.mapper.HsRoleMapper;
import edu.scau.misp.hs.util.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (HsRole)表服务实现类
 *
 * @author makejava
 * @since 2022-03-17 17:03:41
 */
@Service("hsRoleService")
public class HsRoleServiceImpl implements HsRoleService {
    @Resource
    private HsRoleMapper hsRoleMapper;
    @Resource
    private HsStaffMapper hsStaffMapper;
    @Resource
    private HsUserRoleMapper hsUserRoleMapper;
    @Resource
    private HsUserMapper hsUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public HsRole queryById(Integer roleId) {
        return this.hsRoleMapper.queryById(roleId);
    }

    @Override
    public HsRole queryByName(String roleName) {
        return this.hsRoleMapper.queryByName(roleName);
    }


    /**
     * 查询指定行数据
     *
     * @param hsRole 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsRole> queryAll(HsRole hsRole) {
        return this.hsRoleMapper.queryAll(hsRole);
    }


    /**
     * 新增数据
     *
     * @param hsRole 实例对象
     * @return 实例对象
     */
    @Override
    public HsRole insert(HsRole hsRole) {
        if(hsRoleMapper.queryByName(hsRole.getRoleName())!=null){
            throw new ServiceException("该角色名已被占用");
        }else{
            hsRole.setCreateTime(new Date());
            hsRole.setRoleStatus(1);
            this.hsRoleMapper.insert(hsRole);
        }
        return hsRole;
    }

    /**
     * 修改数据
     *
     * @param hsRole 实例对象
     * @return 实例对象
     */
    @Override
    public HsRole update(HsRole hsRole) {
        hsRole.setModifiedTime(new Date());
        this.hsRoleMapper.update(hsRole);
        return this.queryById(hsRole.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean a;
    @Override
    public boolean deleteById(Integer roleId) {
        System.out.println(roleId);
        List<HsStaff> hsStaffList = hsStaffMapper.queryByRoleId(roleId);
        List<HsUserRole> hsUserRoleList = hsUserRoleMapper.queryByRoleId(roleId);
        if (hsStaffList!=null && !hsStaffList.isEmpty()){
            for (HsStaff hsStaff : hsStaffList){
                hsStaffMapper.deleteById(hsStaff.getStaffId());
            }
            if (hsUserRoleList!=null && !hsUserRoleList.isEmpty()){
                for (HsUserRole hsUserRole : hsUserRoleList){
                    hsUserRoleMapper.deleteById(hsUserRole.getUserRoleId());
                    hsUserMapper.deleteById(hsUserRole.getUserId());
                }
            }
        }
        return this.hsRoleMapper.deleteById(roleId) > 0;
    }
}
