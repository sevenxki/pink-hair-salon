package edu.scau.misp.hs.vo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DashboardVo {
    /**
     * 时间
     */
    @JSONField(format="yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date time;

    /**
     * 日
     */
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date day;

    /**
     * 名字
     */
    private String name;

    /**
     * 总金额
     */
    private Double total;

    /**
     * 员工名
     */
    private String staffName;

    /**
     * 次数
     */
    private Integer staffNumber;

    /**
     * 总业绩
     */
    private Double staffPerformance;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 当月散客营业额
     */
    private Double ordinaryTotal;

    /**
     * 当月会员营业额
     */
    private  Double memberTotal;

    /**
     * 当月完成订单数
     */
    private  Integer orderQuantity;

    /**
     * 现有会员
     */
    private Integer newMember;

    /**
     * 会员数
     */
    private Integer value;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getOrdinaryTotal() {
        return ordinaryTotal;
    }

    public void setOrdinaryTotal(Double ordinaryTotal) {
        this.ordinaryTotal = ordinaryTotal;
    }

    public Double getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(Double memberTotal) {
        this.memberTotal = memberTotal;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getNewMember() {
        return newMember;
    }

    public void setNewMember(Integer newMember) {
        this.newMember = newMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public Double getStaffPerformance() {
        return staffPerformance;
    }

    public void setStaffPerformance(Double staffPerformance) {
        this.staffPerformance = staffPerformance;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
