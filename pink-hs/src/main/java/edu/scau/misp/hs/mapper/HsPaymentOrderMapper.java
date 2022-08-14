package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsGrouporder;
import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.vo.ChooseDiscountVO;
import edu.scau.misp.hs.vo.PaymentMethodVo;
import edu.scau.misp.hs.vo.PaymentVo;
import edu.scau.misp.hs.vo.VipDiscountVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * (HsPaymentOrder)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-16 17:04:10
 */
@Mapper
@Repository
public interface HsPaymentOrderMapper {

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
     * 查询指定行数据
     *
     * @param hsPaymentOrder 查询条件
     * @return 对象列表
     */
    List<HsPaymentOrder> queryAll(HsPaymentOrder hsPaymentOrder);

    List<HsPaymentOrder> queryOver(HsPaymentOrder hsPaymentOrder);

    /**
     * 统计总行数
     *
     * @param hsPaymentOrder 查询条件
     * @return 总行数
     */
    long count(HsPaymentOrder hsPaymentOrder);

    /**
     * 新增数据
     *
     * @param hsPaymentOrder 实例对象
     * @return 影响行数
     */
    int insert(HsPaymentOrder hsPaymentOrder);

    /**
     * 修改数据
     *
     * @param hsPaymentOrder 实例对象
     * @return 影响行数
     */
    int update(HsPaymentOrder hsPaymentOrder);

    /**
     * 通过主键删除数据
     *
     * @param paymentOrderId 主键
     * @return 影响行数
     */
    int deleteById(Integer paymentOrderId);

    /**
     * between
     *
     * @param
     * @return 实例对象
     */
    List<HsPaymentOrder> queryDay(Date a1, Date a2);

    /**
     * 查询指定行数据
     *
     * @param
     * @return 对象列表
     */
    List<HsPaymentOrder> all();

    /**
     //     * 新增付款的优惠数据
     //     *
     //     * @param paymentVo 实体
     //     * @return 新增结果
     //     */
    Double addDt(PaymentVo paymentVo);

    /**
     * 查询某客户手机号返回所拥有的优惠券结果
     *
     * @param chooseDiscountVO 筛选条件
     * @return 查询结果
     */
    List<VipDiscountVo> queryAllByPhone(ChooseDiscountVO chooseDiscountVO);

    /**
     * 确定支付，补充支付方式
     *
     * @param paymentMethodVo 实体
     * @return 新增结果
     */
    HsPaymentOrder addPaymentMethod(PaymentMethodVo paymentMethodVo);
}

