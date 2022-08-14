package edu.scau.misp.hs.vo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserVO {
    private Integer userId;

    private String username;

    private String password;

    private String avatar;

    /**
     * 员工序号
     */
    private Integer staffId;
    /**
     * 员工工号
     */
    private Integer staffNumber;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 等级（0为普通员工，1为高级设计师、2为资深设计师、3为技术总监）
     */
    private Integer level;

    private String levelName;
    /**
     * 性别（男为0，女为1）
     */
    private Integer gender;

    private String genderName;
    /**
     * 联系方式
     */
    private Long phoneNumber;
    /**
     * 生日日期
     */
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 员工角色ID
     */
    private Integer roleId;
    /**
     * 角色名字
     */
    private String roleName;

    //工资部分

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
    private String year;
    private String month;

    /**
     * 基本工资
     */
    private Long basicWage;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public Long getBasicWage() {
        return basicWage;
    }

    public void setBasicWage(Long basicWage) {
        this.basicWage = basicWage;
    }
}
