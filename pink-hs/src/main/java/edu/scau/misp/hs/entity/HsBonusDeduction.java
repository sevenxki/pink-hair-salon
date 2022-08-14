package edu.scau.misp.hs.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (HsBonusDeduction)实体类
 *
 * @author makejava
 * @since 2022-05-18 12:40:47
 */
public class HsBonusDeduction implements Serializable {
    private static final long serialVersionUID = 471188694937143275L;
    /**
     * 奖惩记录ID
     */
    private Integer bonusDeductionId;
    /**
     * 工资ID
     */
    private Integer salaryId;
    /**
     * 奖励金额
     */
    private Long bonus;
    /**
     * 扣除金额
     */
    private Long deduction;
    /**
     * 奖励/扣除理由
     */
    private String reason;
    /**
     * 操作人
     */
    private Integer userId;
    /**
     * 操作时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private HsSalary hsSalary;

    public HsSalary getHsSalary() {
        return hsSalary;
    }

    public void setHsSalary(HsSalary hsSalary) {
        this.hsSalary = hsSalary;
    }

    public Integer getBonusDeductionId() {
        return bonusDeductionId;
    }

    public void setBonusDeductionId(Integer bonusDeductionId) {
        this.bonusDeductionId = bonusDeductionId;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Long getBonus() {
        return bonus;
    }

    public void setBonus(Long bonus) {
        this.bonus = bonus;
    }

    public Long getDeduction() {
        return deduction;
    }

    public void setDeduction(Long deduction) {
        this.deduction = deduction;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}

