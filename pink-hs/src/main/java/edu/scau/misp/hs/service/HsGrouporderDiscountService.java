package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsGrouporderDiscount;
import edu.scau.misp.hs.entity.HsVip;

import java.util.List;

/**
 * (HsGrouporderDiscount)表服务接口
 *
 */
public interface HsGrouporderDiscountService {

    /**
     * 通过ID查询单条数据
     *
     * @param grouporderDiscountId 主键
     * @return 实例对象
     */
    HsGrouporderDiscount queryById(Integer grouporderDiscountId);

    /**
     * 查询多条数据
     *
     * @param hsGrouporderDiscount 筛选条件
     * @return 查询结果
     */
    List<HsGrouporderDiscount> queryAll(HsGrouporderDiscount hsGrouporderDiscount);

    /**
     * 新增数据
     *
     * @param hsGrouporderDiscount 实例对象
     * @return 实例对象
     */
    void insert(HsGrouporderDiscount hsGrouporderDiscount);

    /**
     * 修改数据
     *
     * @param hsGrouporderDiscount 实例对象
     * @return 实例对象
     */
    void update(HsGrouporderDiscount hsGrouporderDiscount);

    /**
     * 通过主键删除数据
     *
     * @param grouporderDiscountId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer grouporderDiscountId);

    /**
     * 新增vip订单明细
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    void addNew(HsVip hsVip,String no);


}