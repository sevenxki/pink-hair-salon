package edu.scau.misp.hs.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (HsSalary)实体类
 *
 * @author makejava
 * @since 2022-05-01 02:22:31
 */
public class HsSalary implements Serializable {
    private static final long serialVersionUID = -10012830142654965L;
    /**
     * 工资序号
     */
    private Integer salaryId;
    /**
     * 员工序号
     */
    private Integer staffId;
    /**
     * 工资规则ID
     */
    private Integer wageRulesId;
    /**
     * 总提成
     */
    private Long totalCommission;
    /**
     * 额外奖金
     */
    private Long bonus;
    /**
     * 额外扣除
     */
    private Long deduction;
    /**
     * 总工资
     */
    private Long totalWages;
    /**
     * 工资年月
     */
    private String payTime;
    /**
     * 计算工资时间
     */
    @JSONField(format="yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date payrollTime;
    /**
     * 发放状态，0为未发放，1为已发法
     */
    private String status;
    /**
     * 发放时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date settleTime;

    private HsStaff hsStaff;

    private HsWageRules hsWageRules;

    public HsStaff getHsStaff() {
        return hsStaff;
    }

    public void setHsStaff(HsStaff hsStaff) {
        this.hsStaff = hsStaff;
    }

    public HsWageRules getHsWageRules() {
        return hsWageRules;
    }

    public void setHsWageRules(HsWageRules hsWageRules) {
        this.hsWageRules = hsWageRules;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getWageRulesId() {
        return wageRulesId;
    }

    public void setWageRulesId(Integer wageRulesId) {
        this.wageRulesId = wageRulesId;
    }

    public Long getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(Long totalCommission) {
        this.totalCommission = totalCommission;
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

    public Long getTotalWages() {
        return totalWages;
    }

    public void setTotalWages(Long totalWages) {
        this.totalWages = totalWages;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Date getPayrollTime() {
        return payrollTime;
    }

    public void setPayrollTime(Date payrollTime) {
        this.payrollTime = payrollTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

}

