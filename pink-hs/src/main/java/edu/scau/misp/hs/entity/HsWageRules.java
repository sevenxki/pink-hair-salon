package edu.scau.misp.hs.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (HsWageRules)实体类
 *
 * @author makejava
 * @since 2022-04-29 13:26:21
 */
public class HsWageRules implements Serializable {
    private static final long serialVersionUID = 278367184747202359L;
    /**
     * 工资规则ID
     */
    private Integer wageRulesId;
    /**
     * 员工ID
     */
    private Integer roleId;
    /**
     * 项目等级
     */
    private Integer level;
    /**
     * 基本工资
     */
    private Long basicWage;
    /**
     * 提成比例
     */
    private Long percentage;
    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 角色
     */
    private HsRole hsRole;

    public HsRole getHsRole() {
        return hsRole;
    }

    public void setHsRole(HsRole hsRole) {
        this.hsRole = hsRole;
    }

    public Integer getWageRulesId() {
        return wageRulesId;
    }

    public void setWageRulesId(Integer wageRulesId) {
        this.wageRulesId = wageRulesId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getBasicWage() {
        return basicWage;
    }

    public void setBasicWage(Long basicWage) {
        this.basicWage = basicWage;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

