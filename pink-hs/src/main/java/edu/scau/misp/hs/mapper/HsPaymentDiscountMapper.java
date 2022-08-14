package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsPaymentDiscount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsPaymentDiscount)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-16 17:12:17
 */
@Mapper
@Repository
public interface HsPaymentDiscountMapper {

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
    HsPaymentDiscount  queryByPayment(Integer paymentOrderId);

    /**
     * 查询指定行数据
     *
     * @param hsPaymentDiscount 查询条件
     * @return 对象列表
     */
    List<HsPaymentDiscount> queryAll(HsPaymentDiscount hsPaymentDiscount);

    /**
     * 统计总行数
     *
     * @param hsPaymentDiscount 查询条件
     * @return 总行数
     */
    long count(HsPaymentDiscount hsPaymentDiscount);

    /**
     * 新增数据
     *
     * @param hsPaymentDiscount 实例对象
     * @return 影响行数
     */
    int insert(HsPaymentDiscount hsPaymentDiscount);

    /**
     * 修改数据
     *
     * @param hsPaymentDiscount 实例对象
     * @return 影响行数
     */
    int update(HsPaymentDiscount hsPaymentDiscount);

    /**
     * 通过主键删除数据
     *
     * @param paymentDiscountId 主键
     * @return 影响行数
     */
    int deleteById(Integer paymentDiscountId);

//    /**
//     * 通过付款订单号删除数据
//     *
//     * @param paymentOrderId
//     * @return 影响行数
//     */
//    int deleteByPaymentOrderId(Integer paymentOrderId);

}

