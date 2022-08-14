package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsSaleCommission)实体类
 *
 * @author makejava
 * @since 2022-05-01 01:52:03
 */
public class HsSaleCommission implements Serializable {
    private static final long serialVersionUID = -48521571832632727L;
    /**
     * 业绩提成ID
     */
    private Integer saleCommissionId;
    /**
     * 员工ID
     */
    private Integer staffId;
    /**
     * 付款订单ID
     */
    private Integer paymentOrderId;
    /**
     * 这一单里面的提成金
     */
    private Long commission;

    /**
     * 员工
     */
    private HsStaff hsStaff;

    /**
     * 付款订单
     */
    private HsPaymentOrder hsPaymentOrder;

    public HsPaymentOrder getHsPaymentOrder() {
        return hsPaymentOrder;
    }

    public void setHsPaymentOrder(HsPaymentOrder hsPaymentOrder) {
        this.hsPaymentOrder = hsPaymentOrder;
    }

    public HsStaff getHsStaff() {
        return hsStaff;
    }

    public void setHsStaff(HsStaff hsStaff) {
        this.hsStaff = hsStaff;
    }

    public Integer getSaleCommissionId() {
        return saleCommissionId;
    }

    public void setSaleCommissionId(Integer saleCommissionId) {
        this.saleCommissionId = saleCommissionId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(Integer paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

}

