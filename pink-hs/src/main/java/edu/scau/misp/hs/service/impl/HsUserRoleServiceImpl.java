package edu.scau.misp.hs.service.impl;


import edu.scau.misp.hs.entity.HsRole;
import edu.scau.misp.hs.entity.HsUserRole;
import edu.scau.misp.hs.mapper.HsUserRoleMapper;
import edu.scau.misp.hs.service.HsUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsUserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-03-20 02:13:49
 */
@Service("hsUserRoleService")
public class HsUserRoleServiceImpl implements HsUserRoleService {
    @Resource
    private HsUserRoleMapper hsUserRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userRoleId 主键
     * @return 实例对象
     */
    @Override
    public HsUserRole queryById(Integer userRoleId) {
        return this.hsUserRoleMapper.queryById(userRoleId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsUserRole 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsUserRole> queryAll(HsUserRole hsUserRole) {
        return this.hsUserRoleMapper.queryAll(hsUserRole);
    }


//    /**
//     * 分页查询
//     *
//     * @param hsUserRole 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<HsUserRole> queryByPage(HsUserRole hsUserRole, PageRequest pageRequest) {
//        long total = this.hsUserRoleMapper.count(hsUserRole);
//        return new PageImpl<>(this.hsUserRoleMapper.queryAllByLimit(hsUserRole, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param hsUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public HsUserRole insert(HsUserRole hsUserRole) {
        this.hsUserRoleMapper.insert(hsUserRole);
        return hsUserRole;
    }

    /**
     * 修改数据
     *
     * @param hsUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public HsUserRole update(HsUserRole hsUserRole) {
        this.hsUserRoleMapper.update(hsUserRole);
        return this.queryById(hsUserRole.getUserRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userRoleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userRoleId) {
        return this.hsUserRoleMapper.deleteById(userRoleId) > 0;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public HsUserRole queryByUserId(Integer userId) {
        return this.hsUserRoleMapper.queryByUserId(userId);
    }

    @Override
    public List<HsUserRole> queryByRoleId(Integer roleId) {
        return this.hsUserRoleMapper.queryByRoleId(roleId);
    }

}
