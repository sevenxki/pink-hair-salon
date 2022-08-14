package edu.scau.misp.hs.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (HsCustomer)实体类
 *
 *
 */
public class HsCustomer implements Serializable {
    private static final long serialVersionUID = -60896654077078917L;
    /**
     * 客户ID
     */
    private Integer customerId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 等级（0：普通客户，1：会员客户）
     */
    private Integer level;
    /**
     * 性别（0：男 1：女）
     */
    private Integer gender;
    /**
     * 联系方式
     */
    private Long phoneNumber;
    /**
     * 生日日期
     */
    private Date birthday;
    /**
     * 创建日期
     */
    private Date creationDay;
    /**
     * 角色ID
     */
    private Integer roleId;

    public HsRole getHsRole() {
        return hsRole;
    }

    public void setHsRole(HsRole hsRole) {
        this.hsRole = hsRole;
    }

    public HsUser getHsUser() {
        return hsUser;
    }

    public void setHsUser(HsUser hsUser) {
        this.hsUser = hsUser;
    }

    private HsRole hsRole;

    private HsUser hsUser;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public Date getCreationDay() {
        return creationDay;
    }

    public void setCreationDay(Date creationDay) {
        this.creationDay = creationDay;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}

