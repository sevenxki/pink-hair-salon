package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsProject)实体类
 *
 */
public class HsProject implements Serializable {
    private static final long serialVersionUID = 733837662189859312L;
    
    private Integer projectId;
    
    private String projectName;
    
    private String projectLevel;
    
    private Integer projectNo;
    
    private Double projectPrice;
    
    private Integer kindId;

    //多对一
    private HsKind hsKind;

    private HsStaff hsStaff;

    public HsStaff getHsStaff() {
        return hsStaff;
    }

    public void setHsStaff(HsStaff hsStaff) {
        this.hsStaff = hsStaff;
    }

    public HsKind getHsKind() {
        return hsKind;
    }

    public void setHsKind(HsKind hsKind) {
        this.hsKind = hsKind;
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

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    public Integer getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(Integer projectNo) {
        this.projectNo = projectNo;
    }

    public Double getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(Double projectPrice) {
        this.projectPrice = projectPrice;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

}

