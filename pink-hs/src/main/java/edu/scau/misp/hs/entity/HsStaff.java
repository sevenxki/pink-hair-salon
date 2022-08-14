package edu.scau.misp.hs.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (HsStaff)实体类
 *
 */
public class HsStaff implements Serializable {
    private static final long serialVersionUID = -60540848980425868L;
    /**
     * 员工序号
     */
    private Integer staffId;
    /**
     * 员工工号
     */
    private Integer staffNumber;
    /**
     * 用户名
     */
    private String username;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 等级（0为普通员工，1为高级设计师、2为资深设计师、3为技术总监）
     */
    private String level;

    private String levelName;
    /**
     * 性别（男为0，女为1）
     */
    private Integer gender;
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
     * 员工号创建日期
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDay;
    /**
     * 员工角色ID
     */
    private Integer roleId;

    private HsRole hsRole;

    public HsRole getHsRole() {
        return hsRole;
    }

    public void setHsRole(HsRole hsRole) {
        this.hsRole = hsRole;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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

