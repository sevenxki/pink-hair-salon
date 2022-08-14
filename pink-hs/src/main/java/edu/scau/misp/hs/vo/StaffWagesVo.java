package edu.scau.misp.hs.vo;

import edu.scau.misp.hs.entity.HsSalary;

public class StaffWagesVo {
    private Integer staffId;
    private String staffName;
    private Integer staffNumber;
    private Integer settleStatus;
    private Long basicWage;
    private Long totalCommission;
    private Long totalWages;
    private String year;
    private String month;
    /**
     * 额外奖金
     */
    private Long bonus;
    /**
     * 额外扣除
     */
    private Long deduction;
    private Integer gender;
    private Integer roleId;

    private HsSalary hsSalary;

    public HsSalary getHsSalary() {
        return hsSalary;
    }

    public void setHsSalary(HsSalary hsSalary) {
        this.hsSalary = hsSalary;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public Integer getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
    }

    public Long getBasicWage() {
        return basicWage;
    }

    public void setBasicWage(Long basicWage) {
        this.basicWage = basicWage;
    }

    public Long getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(Long totalCommission) {
        this.totalCommission = totalCommission;
    }

    public Long getTotalWages() {
        return totalWages;
    }

    public void setTotalWages(Long totalWages) {
        this.totalWages = totalWages;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
}
