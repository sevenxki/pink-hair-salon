package edu.scau.misp.hs.vo;

public class VipDiscountVo {
    /**
     * 会员电话号码
     */
    private String vipPhone;
    /**
     * 优惠ID（包括会员充值项目和团购项目）
     */
    private Integer discountId;
    /**
     * 优惠名字
     */
    private String discountName;
    /**
     * 剩余护理次数
     */
    private Integer vipHaircare;
    /**
     * 剩余理发次数
     */
    private Integer vipHaircut;
    /**
     * 会员卡余额
     */
    private Double vipBalance;

    public String getVipPhone() {
        return vipPhone;
    }

    public void setVipPhone(String vipPhone) {
        this.vipPhone = vipPhone;
    }

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

    public Integer getVipHaircare() {
        return vipHaircare;
    }

    public void setVipHaircare(Integer vipHaircare) {
        this.vipHaircare = vipHaircare;
    }

    public Integer getVipHaircut() {
        return vipHaircut;
    }

    public void setVipHaircut(Integer vipHaircut) {
        this.vipHaircut = vipHaircut;
    }

    public Double getVipBalance() {
        return vipBalance;
    }

    public void setVipBalance(Double vipBalance) {
        this.vipBalance = vipBalance;
    }
}
