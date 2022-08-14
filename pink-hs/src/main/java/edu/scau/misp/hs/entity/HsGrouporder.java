package edu.scau.misp.hs.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (HsGrouporder)实体类
 *
 */
public class HsGrouporder implements Serializable {
    private static final long serialVersionUID = 310460628007423589L;
    /**
     * 购买优惠的订单id
     */
    private Integer grouporderId;
    /**
     * 购买优惠的订单的编号
     */
    private String grouporderNo;
    /**
     * 时间
     */
    private Date grouporderDatetime;
    /**
     * 总金额
     */
    private Double grouporderTotal;


    public Integer getGrouporderId() {
        return grouporderId;
    }

    public void setGrouporderId(Integer grouporderId) {
        this.grouporderId = grouporderId;
    }

    public String getGrouporderNo() {
        return grouporderNo;
    }

    public void setGrouporderNo(String grouporderNo) {
        this.grouporderNo = grouporderNo;
    }

    public Date getGrouporderDatetime() {
        return grouporderDatetime;
    }

    public void setGrouporderDatetime(Date grouporderDatetime) {
        this.grouporderDatetime = grouporderDatetime;
    }

    public Double getGrouporderTotal() {
        return grouporderTotal;
    }

    public void setGrouporderTotal(Double grouporderTotal) {
        this.grouporderTotal = grouporderTotal;
    }

}

