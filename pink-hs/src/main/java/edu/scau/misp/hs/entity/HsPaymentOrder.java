package edu.scau.misp.hs.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (HsPaymentOrder)实体类
 *
 * @author makejava
 * @since 2022-04-16 17:04:10
 */
public class HsPaymentOrder implements Serializable {
    private static final long serialVersionUID = 532796051381405984L;
    /**
     * 付款订单序号
     */
    private Integer paymentOrderId;
    /**
     * 订单序号
     */
    private Integer orderId;
    /**
     * 付款时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    /**
     * 手机号
     */
    private String phonePayment;
    /**
     * 付款方式（0为现金，1为微信，2为支付宝，3为银行卡，4为其他）
     */
    private Integer paymentMethod;
    /**
     * 0为无，2为剪发，3为护理
     */
    private Integer useDiscount;
    /**
     * 优惠名字
     */
    private String discountName;
    /**
     * 是否会使用会员卡，0为无，1为有
     */
    private Integer useVip;
    /**
     * 付款状态，0为生成付款状态，1已完成付款
     */
    private Integer status;
    /**
     * 总金额
     */
    private Double totalPayment;

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

    public Integer getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(Integer paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPhonePayment() {
        return phonePayment;
    }

    public void setPhonePayment(String phonePayment) {
        this.phonePayment = phonePayment;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getUseDiscount() {
        return useDiscount;
    }

    public void setUseDiscount(Integer useDiscount) {
        this.useDiscount = useDiscount;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Integer getUseVip() {
        return useVip;
    }

    public void setUseVip(Integer useVip) {
        this.useVip = useVip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

}

