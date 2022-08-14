package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.vo.*;

import java.util.List;

/**
 * (HsPaymentOrder)表服务接口
 *
 * @author makejava
 * @since 2022-04-16 17:04:10
 */
public interface HsPaymentOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param paymentOrderId 主键
     * @return 实例对象
     */
    HsPaymentOrder queryById(Integer paymentOrderId);

    List<HsPaymentOrder> queryByPhone(String phonePayment);

    HsPaymentOrder queryByOrder(Integer orderId);

    /**
     *查询多条数据
     *
     * @param hsPaymentOrder 筛选条件
     * @return 查询结果
     */
    List<HsPaymentOrder> queryAll(HsPaymentOrder hsPaymentOrder);

    List<HsPaymentOrder> queryOver(HsPaymentOrder hsPaymentOrder);
    /**
     * 新增数据
     *
     * @param hsPaymentOrder 实例对象
     * @return 实例对象
     */
    HsPaymentOrder insert(HsPaymentOrder hsPaymentOrder);

    /**
     * 修改数据
     *
     * @param hsPaymentOrder 实例对象
     * @return 实例对象
     */
    HsPaymentOrder update(HsPaymentOrder hsPaymentOrder);

    /**
     * 通过主键删除数据
     *
     * @param paymentOrderId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer paymentOrderId);

    Double addDt(PaymentVo paymentVo);

    List<VipDiscountVo> queryAllByPhone(ChooseDiscountVO chooseDiscountVO);

    HsPaymentOrder addPaymentMethod(PaymentMethodVo paymentMethodVo);
}
