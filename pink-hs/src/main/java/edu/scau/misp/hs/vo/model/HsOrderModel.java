package edu.scau.misp.hs.vo.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HsOrderModel {
    private Integer orderId;
    private Integer projectId;
    private String projectName;
    private Double projectPrice;
    private Integer staffHairstylistId;
    private String staffHairstylistName;
    private Integer staffTechnicianId;
    private String staffTechnicianName;
    private Integer Haircare;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(Double projectPrice) {
        this.projectPrice = projectPrice;
    }

    public Integer getStaffHairstylistId() {
        return staffHairstylistId;
    }

    public void setStaffHairstylistId(Integer staffHairstylistId) {
        this.staffHairstylistId = staffHairstylistId;
    }

    public String getStaffHairstylistName() {
        return staffHairstylistName;
    }

    public void setStaffHairstylistName(String staffHairstylistName) {
        this.staffHairstylistName = staffHairstylistName;
    }

    public Integer getStaffTechnicianId() {
        return staffTechnicianId;
    }

    public void setStaffTechnicianId(Integer staffTechnicianId) {
        this.staffTechnicianId = staffTechnicianId;
    }

    public String getStaffTechnicianName() {
        return staffTechnicianName;
    }

    public void setStaffTechnicianName(String staffTechnicianName) {
        this.staffTechnicianName = staffTechnicianName;
    }

    public Integer getHaircare() {
        return Haircare;
    }

    public void setHaircare(Integer haircare) {
        Haircare = haircare;
    }
}
