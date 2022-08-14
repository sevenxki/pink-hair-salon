package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsPaymentDiscount)实体类
 *
 * @author makejava
 * @since 2022-04-16 17:12:18
 */
public class HsPaymentDiscount implements Serializable {
    private static final long serialVersionUID = 582161810404029270L;
    /**
     * 付款优惠券ID
     */
    private Integer paymentDiscountId;
    /**
     * 优惠券ID
     */
    private Integer discountId;
    /**
     * 优惠券使用次数
     */
    private Integer count;
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
     * 优惠
     */
    private HsDiscount hsDiscount;
    public HsDiscount getHsDiscount() {
        return hsDiscount;
    }
    public void setHsDiscount(HsDiscount hsDiscount) {
        this.hsDiscount = hsDiscount;
    }


    public Integer getPaymentDiscountId() {
        return paymentDiscountId;
    }

    public void setPaymentDiscountId(Integer paymentDiscountId) {
        this.paymentDiscountId = paymentDiscountId;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(Integer paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

}

