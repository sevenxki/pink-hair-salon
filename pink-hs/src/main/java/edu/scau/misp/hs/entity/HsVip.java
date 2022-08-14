package edu.scau.misp.hs.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;


/**
 * (HsVip)实体类
 *
 */
public class HsVip implements Serializable {
    private static final long serialVersionUID = 448879285850584560L;

    private Integer vipId;

    private String vipName;
    /**
     * 性别（1为男，2为女）
     */
    private Integer vipGender;
    /**
     * 会员电话号码
     */
    private String vipPhone;
    /**
     * 会员生日
     */
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vipBirthday;
    /**
     * 会员卡余额
     */
    private Double vipBalance;
    /**
     * 剩余理发次数
     */
    private Integer vipHaircut;

    public Integer getAccumulationId() {
        return accumulationId;
    }

    public void setAccumulationId(Integer accumulationId) {
        this.accumulationId = accumulationId;
    }

    public Double getAccumulationTotal() {
        return accumulationTotal;
    }

    public void setAccumulationTotal(Double accumulationTotal) {
        this.accumulationTotal = accumulationTotal;
    }

    /**
     * 累积id
     */
    private Integer accumulationId;
    /**
     * 会员累积消费的总金额
     */
    private Double accumulationTotal;

    public HsAccumulation getHsAccumulation() {
        return hsAccumulation;
    }

    public void setHsAccumulation(HsAccumulation hsAccumulation) {
        this.hsAccumulation = hsAccumulation;
    }

    /**
     * 剩余护理次数
     */
    private Integer vipHaircare;

    private HsAccumulation hsAccumulation;


    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public Integer getVipGender() {
        return vipGender;
    }

    public void setVipGender(Integer vipGender) {
        this.vipGender = vipGender;
    }

    public String getVipPhone() {
        return vipPhone;
    }

    public void setVipPhone(String vipPhone) {
        this.vipPhone = vipPhone;
    }

    public Date getVipBirthday() {
        return vipBirthday;
    }

    public void setVipBirthday(Date vipBirthday) {
        this.vipBirthday = vipBirthday;
    }

    public Double getVipBalance() {
        return vipBalance;
    }

    public void setVipBalance(Double vipBalance) {
        this.vipBalance = vipBalance;
    }

    public Integer getVipHaircut() {
        return vipHaircut;
    }

    public void setVipHaircut(Integer vipHaircut) {
        this.vipHaircut = vipHaircut;
    }

    public Integer getVipHaircare() {
        return vipHaircare;
    }

    public void setVipHaircare(Integer vipHaircare) {
        this.vipHaircare = vipHaircare;
    }

}

