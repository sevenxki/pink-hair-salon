package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsDiscount;
import java.util.List;

/**
 * (HsDiscount)表服务接口
 *
 */
public interface HsDiscountService {

    /**
     * 通过ID查询单条数据
     *
     * @param discountId 主键
     * @return 实例对象
     */
    HsDiscount queryById(Integer discountId);

    /**
     * 查询多条数据
     *
     * @param hsDiscount 筛选条件
     * @return 查询结果
     */
    List<HsDiscount> queryAll(HsDiscount hsDiscount);

    /**
     * 新增数据
     *
     * @param hsDiscount 实例对象
     * @return 实例对象
     */
    void insert(HsDiscount hsDiscount);

    /**
     * 修改数据
     *
     * @param hsDiscount 实例对象
     * @return 实例对象
     */
    void update(HsDiscount hsDiscount);

    /**
     * 通过主键删除数据
     *
     * @param discountId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer discountId);

}