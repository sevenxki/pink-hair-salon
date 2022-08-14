package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsRoleMenu;
import java.util.List;

/**
 * (HsRoleMenu)表服务接口
 *
 */
public interface HsRoleMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleMenuId 主键
     * @return 实例对象
     */
    HsRoleMenu queryById(Integer roleMenuId);

    /**
     * 查询多条数据
     *
     * @param hsRoleMenu 筛选条件
     * @return 查询结果
     */
    List<HsRoleMenu> queryAll(HsRoleMenu hsRoleMenu);

    /**
     * 新增数据
     *
     * @param hsRoleMenu 实例对象
     * @return 实例对象
     */
    void insert(HsRoleMenu hsRoleMenu);

    /**
     * 修改数据
     *
     * @param hsRoleMenu 实例对象
     * @return 实例对象
     */
    void update(HsRoleMenu hsRoleMenu);

    /**
     * 通过主键删除数据
     *
     * @param roleMenuId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleMenuId);

}