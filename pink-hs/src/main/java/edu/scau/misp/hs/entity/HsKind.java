package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsKind)实体类
 *
 */
public class HsKind implements Serializable {
    private static final long serialVersionUID = -37464733255614026L;
    /**
     * 项目类型ID
     */
    private Integer kindId;
    /**
     * 类型编号
     */
    private String kindNo;
    /**
     * 类型名称
     */
    private String kindName;


    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    public String getKindNo() {
        return kindNo;
    }

    public void setKindNo(String kindNo) {
        this.kindNo = kindNo;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

}
