package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsDiscount)实体类
 *
 */
public class HsDiscount implements Serializable {
    private static final long serialVersionUID = 916743158592053924L;
    /**
     * 优惠ID（包括会员充值项目和团购项目）
     */
    private Integer discountId;
    /**
     * 优惠名字
     */
    private String discountName;
    /**
     * 优惠编号
     */
    private Integer discountNo;
    /**
     * 优惠所需金额
     */
    private Long discountMoney;
    /**
     * 一个优惠所享受的次数
     */
    private Integer discountTimes;


    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Integer getDiscountNo() {
        return discountNo;
    }

    public void setDiscountNo(Integer discountNo) {
        this.discountNo = discountNo;
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getDiscountTimes() {
        return discountTimes;
    }

    public void setDiscountTimes(Integer discountTimes) {
        this.discountTimes = discountTimes;
    }

}

