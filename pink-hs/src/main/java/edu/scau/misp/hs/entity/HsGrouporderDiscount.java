package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsGrouporderDiscount)实体类
 *
 */
public class HsGrouporderDiscount implements Serializable {
    private static final long serialVersionUID = -21044925329954755L;
    /**
     * 充钱和买卡的明细
     */
    private Integer grouporderDiscountId;
    /**
     * 购买优惠的订单的id
     */
    private Integer grouporderId;
    /**
     * 优惠的id
     */
    private Integer discountId;
    /**
     * 销售数量
     */
    private Integer quantity;
    /**
     * 金额
     */
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGrouporderDiscountId() {
        return grouporderDiscountId;
    }

    public void setGrouporderDiscountId(Integer grouporderDiscountId) {
        this.grouporderDiscountId = grouporderDiscountId;
    }

    public Integer getGrouporderId() {
        return grouporderId;
    }

    public void setGrouporderId(Integer grouporderId) {
        this.grouporderId = grouporderId;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

