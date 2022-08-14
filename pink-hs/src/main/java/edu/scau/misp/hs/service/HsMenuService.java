package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsMenu;
import java.util.List;

/**
 * (HsMenu)表服务接口
 *
 */
public interface HsMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    HsMenu queryById(Integer menuId);

    /**
     * 查询多条数据
     *
     * @param hsMenu 筛选条件
     * @return 查询结果
     */
    List<HsMenu> queryAll(HsMenu hsMenu);

    /**
     * 新增数据
     *
     * @param hsMenu 实例对象
     * @return 实例对象
     */
    void insert(HsMenu hsMenu);

    /**
     * 修改数据
     *
     * @param hsMenu 实例对象
     * @return 实例对象
     */
    void update(HsMenu hsMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer menuId);

}