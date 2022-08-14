package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsKind;
import java.util.List;

/**
 * (HsKind)表服务接口
 *
 */
public interface HsKindService {

    /**
     * 通过ID查询单条数据
     *
     * @param kindId 主键
     * @return 实例对象
     */
    HsKind queryById(Integer kindId);

    /**
     * 查询多条数据
     *
     * @param hsKind 筛选条件
     * @return 查询结果
     */
    List<HsKind> queryAll(HsKind hsKind);

    /**
     * 新增数据
     *
     * @param hsKind 实例对象
     * @return 实例对象
     */
    void insert(HsKind hsKind);

    /**
     * 修改数据
     *
     * @param hsKind 实例对象
     * @return 实例对象
     */
    void update(HsKind hsKind);

    /**
     * 通过主键删除数据
     *
     * @param kindId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer kindId);

}