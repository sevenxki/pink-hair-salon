package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsAccumulation)实体类
 *
 */
public class HsAccumulation implements Serializable {
    private static final long serialVersionUID = -53061875034640931L;
    /**
     * 累积id
     */
    private Integer accumulationId;
    /**
     * 会员id
     */
    private Integer vipId;
    /**
     * 会员累积消费的总金额
     */
    private Double accumulationTotal;

    private HsVip hsVip;

    public HsVip getHsVip() {
        return hsVip;
    }

    public void setHsVip(HsVip hsVip) {
        this.hsVip = hsVip;
    }

    public Integer getAccumulationId() {
        return accumulationId;
    }

    public void setAccumulationId(Integer accumulationId) {
        this.accumulationId = accumulationId;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Double getAccumulationTotal() {
        return accumulationTotal;
    }

    public void setAccumulationTotal(Double accumulationTotal) {
        this.accumulationTotal = accumulationTotal;
    }

}

