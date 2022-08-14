package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsPaymentVip)实体类
 *
 * @author makejava
 * @since 2022-04-16 17:12:07
 */
public class HsPaymentVip implements Serializable {
    private static final long serialVersionUID = -24366355961340225L;
    /**
     * 付款会员卡ID
     */
    private Integer paymentVipId;
    /**
     * 会员卡ID
     */
    private Integer vipId;
    /**
     * 使用金额
     */
    private Double useNumber;
    /**
     * 付款订单号
     */
    private Integer paymentOrderId;

    /**
     * 订单
     */
    private HsOrder hsOrder;
    public HsOrder getHsOrder() {
        return hsOrder;
    }
    public void setHsOrder(HsOrder hsOrder) {
        this.hsOrder = hsOrder;
    }

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

    /**
     * VIP客户
     */
    private HsVip hsVip;
    public HsVip getHsVip() {
        return hsVip;
    }
    public void setHsVip(HsVip hsVip) {
        this.hsVip = hsVip;
    }


    public Integer getPaymentVipId() {
        return paymentVipId;
    }

    public void setPaymentVipId(Integer paymentVipId) {
        this.paymentVipId = paymentVipId;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Double getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Double useNumber) {
        this.useNumber = useNumber;
    }

    public Integer getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(Integer paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

}

