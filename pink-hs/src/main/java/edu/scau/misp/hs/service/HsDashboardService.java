package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsAccumulation;
import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.vo.DashboardVo;

import java.util.List;

public interface HsDashboardService {
    /**
     * 计算普通消费总额
     *
     * @param
     * @return 实例对象
     */
    Double CumulativeTotalOrder(HsPaymentOrder hsPaymentOrder);

    /**
     * 按每个月计算普通订单的总金额
     *
     * @param
     * @return
     */
    List<DashboardVo> countTotalByMonth();

    /**
     * 本月的总金额、散客营业额、会员营业额、完成订单数、现有会员
     *
     * @param
     * @return
     */
    DashboardVo thisMonth();

    /**
     * 按每个月计算总金额
     *
     * @param
     * @return
     */
    List<DashboardVo> countAmountByMonth();



    /**
     * 按月的总的门店业绩 现有月的扩充
     *
     * @param
     * @return
     */
    List<DashboardVo> allMonthTotal();

    /**
     * 计算总会员页面消费
     *
     * @param
     * @return 实例对象
     */
    Double CumulativeTotal(HsAccumulation hsAccumulation);

    /**
     * 会员卡余额充值总值、购买理发卡总值、购买护理卡总值
     *
     * @param
     * @return 实例对象
     */
    Double[] queryAllAmount();

    /**
     * 按月的总的门店业绩
     *
     * @param
     * @return
     */
    List<DashboardVo> allTotal();

    /**
     * 按月的总的门店业绩的时间条
     *
     * @param
     * @return
     */
    String[] allMonthTotalTime();

    /**
     * 按月的总的门店业绩的总额条
     *
     * @param
     * @return
     */
    Double[] allMonthTotalTotal();

    /**
     * 近半年总的门店业绩
     *
     * @param
     * @return
     */
    List<DashboardVo> halfYear();

    /**
     * 近半年总的门店业绩--time
     *
     * @param
     * @return
     */
    String[] halfYearTime();

    /**
     * 近半年总的门店业绩--total
     *
     * @param
     * @return
     */
    Double[] halfYearTotal();

    /**
     * 近七天总的门店业绩
     *
     * @param
     * @return
     */
    List<DashboardVo> nearSeven() ;

    /**
     * 近七天总的门店业绩--time
     *
     * @param
     * @return
     */
    String[] nearSevenTime();

    /**
     * 近七天总的门店业绩--total
     *
     * @param
     * @return
     */
    Double[] nearSevenTotal();

    /**
     * 近三十天总的门店业绩(6个点)
     *
     * @param
     * @return
     */
    List<DashboardVo> nearMonth();

    /**
     * 近三十天总的门店业绩--time
     *
     * @param
     * @return
     */
    String[] nearMonthTime();

    /**
     * 近三十天总的门店业绩--total
     *
     * @param
     * @return
     */
    Double[] nearMonthTotal();

    /**
     * 会员数
     *
     * @param
     * @return
     */
    List<DashboardVo> vipNumber();

    /**
     * 员工业绩
     *
     * @param
     * @return
     */
    List<DashboardVo> performance();

    /**
     * 员工业绩--name
     *
     * @param
     * @return
     */
    String[] performanceName();

    /**
     * 员工业绩--总额
     *
     * @param
     * @return
     */
    Double[] performanceTotal();


}
