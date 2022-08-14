package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsPaymentVip;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsPaymentVip)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-16 17:12:06
 */
@Mapper
@Repository
public interface HsPaymentVipMapper {

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
     * 查询指定行数据
     *
     * @param hsPaymentVip 查询条件
     * @return 对象列表
     */
    List<HsPaymentVip> queryAll(HsPaymentVip hsPaymentVip);

    /**
     * 统计总行数
     *
     * @param hsPaymentVip 查询条件
     * @return 总行数
     */
    long count(HsPaymentVip hsPaymentVip);

    /**
     * 新增数据
     *
     * @param hsPaymentVip 实例对象
     * @return 影响行数
     */
    int insert(HsPaymentVip hsPaymentVip);

    /**
     * 修改数据
     *
     * @param hsPaymentVip 实例对象
     * @return 影响行数
     */
    int update(HsPaymentVip hsPaymentVip);

    /**
     * 通过主键删除数据
     *
     * @param paymentVipId 主键
     * @return 影响行数
     */
    int deleteById(Integer paymentVipId);

//    /**
//     * 通过付款订单号删除数据
//     *
//     * @param paymentOrderId
//     * @return 影响行数
//     */
//    int deleteByPaymentOrderId(Integer paymentOrderId);

}

