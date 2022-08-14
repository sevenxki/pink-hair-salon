package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsPaymentVip;
import java.util.List;

/**
 * (HsPaymentVip)表服务接口
 *
 * @author makejava
 * @since 2022-04-16 17:12:08
 */
public interface HsPaymentVipService {

    /**
     * 通过ID查询单条数据
     *
     * @param paymentVipId 主键
     * @return 实例对象
     */
    HsPaymentVip queryById(Integer paymentVipId);

    /**
     * 通过付款订单ID查询单条数据
     *
     * @param paymentOrderId 主键
     * @return 实例对象
     */
    HsPaymentVip queryByPayment(Integer paymentOrderId);

    /**
     *查询多条数据
     *
     * @param hsPaymentVip 筛选条件
     * @return 查询结果
     */
    List<HsPaymentVip> queryAll(HsPaymentVip hsPaymentVip);

    /**
     * 新增数据
     *
     * @param hsPaymentVip 实例对象
     * @return 实例对象
     */
    HsPaymentVip insert(HsPaymentVip hsPaymentVip);

    /**
     * 修改数据
     *
     * @param hsPaymentVip 实例对象
     * @return 实例对象
     */
    HsPaymentVip update(HsPaymentVip hsPaymentVip);

    /**
     * 通过主键删除数据
     *
     * @param paymentVipId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer paymentVipId);

//    /**
//     * 通过付款订单号删除数据
//     *
//     * @param paymentOrderId
//     * @return 是否成功
//     */
//    boolean deleteByPaymentOrderId(Integer paymentOrderId);
}
