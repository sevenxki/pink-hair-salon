package edu.scau.misp.hs.entity;


import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (HsOrder)实体类
 *
 */
public class HsOrder implements Serializable {
    private static final long serialVersionUID = 857408330743694554L;

    private Integer orderId;

    private Integer projectId;

    private Integer staffHairstylistId;
    private String staffHairstylistName;
    private Integer staffTechnicianId;

    private Integer haircare;
    private String haircareName;

    private Double total;

    private Integer status;

    private Integer userId;
//    private String userName;//账号名
    private String userStaffName;//人名
    //用户
    private HsUser hsUser;


    public String getUserStaffName() {
        return userStaffName;
    }

    public void setUserStaffName(String userStaffName) {
        this.userStaffName = userStaffName;
    }


    public HsUser getHsUser() {
        return hsUser;
    }

    public void setHsUser(HsUser hsUser) {
        this.hsUser = hsUser;
    }

    //名称
    public String getStaffHairstylistName() {
        return staffHairstylistName;
    }

    public void setStaffHairstylistName(String staffHairstylistName) {
        this.staffHairstylistName = staffHairstylistName;
    }

    public String getHaircareName() {
        return haircareName;
    }

    public void setHaircareName(String haircareName) {
        this.haircareName = haircareName;
    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    //项目
    private HsProject hsProject;
    public HsProject getHsProject() {
        return hsProject;
    }

    public void setHsProject(HsProject hsProject) {
        this.hsProject = hsProject;
    }

    //员工
    private HsStaff hsStaff1;

    public HsStaff getHsStaff1() {
        return hsStaff1;
    }

    public void setHsStaff1(HsStaff hsStaff1) {
        this.hsStaff1 = hsStaff1;
    }

    /**
     * 下单时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderCreatetime;

    public Date getOrderCreatetime() {
        return orderCreatetime;
    }

    public void setOrderCreatetime(Date orderCreatetime) {
        this.orderCreatetime = orderCreatetime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getStaffHairstylistId() {
        return staffHairstylistId;
    }

    public void setStaffHairstylistId(Integer staffHairstylistId) {
        this.staffHairstylistId = staffHairstylistId;
    }

    public Integer getStaffTechnicianId() {
        return staffTechnicianId;
    }

    public void setStaffTechnicianId(Integer staffTechnicianId) {
        this.staffTechnicianId = staffTechnicianId;
    }

    public Integer getHaircare() {
        return haircare;
    }

    public void setHaircare(Integer haircare) {
        this.haircare = haircare;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

