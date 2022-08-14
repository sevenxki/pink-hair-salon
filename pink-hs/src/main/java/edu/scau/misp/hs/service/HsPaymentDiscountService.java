package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsPaymentDiscount;
import java.util.List;

/**
 * (HsPaymentDiscount)表服务接口
 *
 * @author makejava
 * @since 2022-04-16 17:12:18
 */
public interface HsPaymentDiscountService {

    /**
     * 通过ID查询单条数据
     *
     * @param paymentDiscountId 主键
     * @return 实例对象
     */
    HsPaymentDiscount queryById(Integer paymentDiscountId);

    /**
     * 通过付款订单ID查询单条数据
     *
     * @param paymentOrderId 主键
     * @return 实例对象
     */
    HsPaymentDiscount queryByPayment(Integer paymentOrderId);

    /**
     *查询多条数据
     *
     * @param hsPaymentDiscount 筛选条件
     * @return 查询结果
     */
    List<HsPaymentDiscount> queryAll(HsPaymentDiscount hsPaymentDiscount);

    /**
     * 新增数据
     *
     * @param hsPaymentDiscount 实例对象
     * @return 实例对象
     */
    HsPaymentDiscount insert(HsPaymentDiscount hsPaymentDiscount);

    /**
     * 修改数据
     *
     * @param hsPaymentDiscount 实例对象
     * @return 实例对象
     */
    HsPaymentDiscount update(HsPaymentDiscount hsPaymentDiscount);

    /**
     * 通过主键删除数据
     *
     * @param paymentDiscountId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer paymentDiscountId);

//    /**
//     * 通过付款订单号删除数据
//     *
//     * @param paymentOrderId
//     * @return 是否成功
//     */
//    boolean deleteByPaymentOrderId(Integer paymentOrderId);
}
