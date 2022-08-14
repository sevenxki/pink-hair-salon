package edu.scau.misp.web.controller;


import edu.scau.misp.hs.entity.HsAccumulation;
import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.service.*;
import edu.scau.misp.hs.vo.DashboardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("hsDashboard")
public class HsDashboardController {
    /**
     * 服务对象
     */
    @Autowired
    private HsDashboardService hsDashboardService;



    /**
     * 计算普通消费总额
     *
     * @param
     * @return 实例对象
     */
    @GetMapping("/CumulativeTotalOrder")
    public Double CumulativeTotalOrder(HsPaymentOrder hsPaymentOrder){
        return this.hsDashboardService.CumulativeTotalOrder(hsPaymentOrder);
    }

    /**
     * 计算总会员页面消费
     *
     * @param
     * @return 实例对象
     */
    @GetMapping("/CumulativeTotal")
    public Double CumulativeTotal(HsAccumulation hsAccumulation){
        return this.hsDashboardService.CumulativeTotal(hsAccumulation);
    }

    /**
     * 会员卡余额充值总值、购买理发卡总值、购买护理卡总值
     *
     * @param
     * @return 实例对象
     */
    @GetMapping("/queryAllAmount")
    public Double[] queryAllAmount(){
        return this.hsDashboardService.queryAllAmount();
    }

    /**
     * 按每个月计算会员团购的总金额
     *
     * @param
     * @return
     */
    @GetMapping("/countAmountByMonth")
    public List<DashboardVo> countAmountByMonth(){
        return this.hsDashboardService.countAmountByMonth();
    }

    /**
     * 按每个月计算普通订单的总金额
     *
     * @param
     * @return
     */
    @GetMapping("/countTotalByMonth")
    public List<DashboardVo> countTotalByMonth(){
        return this.hsDashboardService.countTotalByMonth();
    }

    /**
     * 本月的总金额、散客营业额、会员营业额、完成订单数、现有会员
     *
     * @param
     * @return
     */
    @GetMapping("/thisMonth")
    public DashboardVo thisMonth(){
        return this.hsDashboardService.thisMonth();
    }

    /**
     * 按月的总的门店业绩
     *
     * @param
     * @return
     */
    @GetMapping("/allTotal")
    public List<DashboardVo> allTotal(){
        return this.hsDashboardService.allTotal();
    }


    /**
     * 按月的总的门店业绩 现有月的扩充
     *
     * @param
     * @return
     */
    @GetMapping("/allMonthTotal")
    public List<DashboardVo> allMonthTotal(){
        return this.hsDashboardService.allMonthTotal();
    }

    /**
     * 按月的总的门店业绩-time
     *
     * @param
     * @return
     */
    @GetMapping("/allMonthTotalTime")
    public String[] allMonthTotalTime(){
        return this.hsDashboardService.allMonthTotalTime();
    }

    /**
     * 按月的总的门店业绩--total
     *
     * @param
     * @return
     */
    @GetMapping("/allMonthTotalTotal")
    public Double[] allMonthTotalTotal(){
        return this.hsDashboardService.allMonthTotalTotal();
    }

    /**
     * 近半年总的门店业绩
     *
     * @param
     * @return
     */
    @GetMapping("/halfYear")
    public  List<DashboardVo> halfYear() {
        return this.hsDashboardService.halfYear();
    }

    /**
     * 近半年总的门店业绩--time
     *
     * @param
     * @return
     */
    @GetMapping("/halfYearTime")
    public  String[] halfYearTime() {
        return this.hsDashboardService.halfYearTime();
    }

    /**
     * 近半年总的门店业绩--total
     *
     * @param
     * @return
     */
    @GetMapping("/halfYearTotal")
    public  Double[] halfYearTotal() {
        return this.hsDashboardService.halfYearTotal();
    }


    /**
     * 近三十天总的门店业绩(6个点)
     *
     * @param
     * @return
     */
    @GetMapping("/nearMonth")
    public List<DashboardVo> nearMonth() {
        return this.hsDashboardService.nearMonth();
    }

    /**
     * 近三十天总的门店业绩--time
     *
     * @param
     * @return
     */
    @GetMapping("/nearMonthTime")
    public  String[] nearMonthTime() {
        return this.hsDashboardService.nearMonthTime();
    }

    /**
     * 近三十天总的门店业绩--total
     *
     * @param
     * @return
     */
    @GetMapping("/nearMonthTotal")
    public  Double[] nearMonthTotal() {
        return this.hsDashboardService.nearMonthTotal();
    }

    /**
     * 近七天总的门店业绩
     *
     * @param
     * @return
     */
    @GetMapping("/nearSeven")
    public List<DashboardVo> nearSeven() {
        return this.hsDashboardService.nearSeven();
    }

    /**
     * 近七天总的门店业绩--time
     *
     * @param
     * @return
     */
    @GetMapping("/nearSevenTime")
    public  String[] nearSevenTime() {
        return this.hsDashboardService.nearSevenTime();
    }

    /**
     * 近七天总的门店业绩--total
     *
     * @param
     * @return
     */
    @GetMapping("/nearSevenTotal")
    public  Double[] nearSevenTotal() {
        return this.hsDashboardService.nearSevenTotal();
    }


    /**
     * 会员数
     *
     * @param
     * @return
     */
    @GetMapping("/vipNumber")
    public List<DashboardVo> vipNumber() {
        return this.hsDashboardService.vipNumber();
    }

    /**
     * 员工业绩
     *
     * @param
     * @return
     */
    @GetMapping("/performance")
    public List<DashboardVo> performance() {
        return this.hsDashboardService.performance();
    }

    /**
     * 员工业绩--name
     *
     * @param
     * @return
     */
    @GetMapping("/performanceName")
    public String[] performanceName() {
        return this.hsDashboardService.performanceName();
    }

    /**
     * 员工业绩--总额
     *
     * @param
     * @return
     */
    @GetMapping("/performanceTotal")
    public Double[] performanceTotal() {
        return this.hsDashboardService.performanceTotal();
    }


}
