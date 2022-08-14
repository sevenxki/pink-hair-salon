package edu.scau.misp.hs.service;


import edu.scau.misp.hs.entity.HsUserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HsUserRole)表服务接口
 *
 * @author makejava
 * @since 2022-03-20 02:13:48
 */
public interface HsUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param userRoleId 主键
     * @return 实例对象
     */
    HsUserRole queryById(Integer userRoleId);

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    HsUserRole queryByUserId(Integer userId);

    List<HsUserRole> queryByRoleId(Integer roleId);

//    /**
//     * 分页查询
//     *
//     * @param hsUserRole 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    Page<HsUserRole> queryByPage(HsUserRole hsUserRole, PageRequest pageRequest);

    /**
     * 查询指定行数据
     *
     * @param hsUserRole 筛选条件
     * @return 对象列表
     */
    List<HsUserRole> queryAll(HsUserRole hsUserRole);

    /**
     * 新增数据
     *
     * @param hsUserRole 实例对象
     * @return 实例对象
     */
    HsUserRole insert(HsUserRole hsUserRole);

    /**
     * 修改数据
     *
     * @param hsUserRole 实例对象
     * @return 实例对象
     */
    HsUserRole update(HsUserRole hsUserRole);

    /**
     * 通过主键删除数据
     *
     * @param userRoleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userRoleId);

}
