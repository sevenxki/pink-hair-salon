package edu.scau.misp.hs.vo;

import edu.scau.misp.hs.entity.HsPaymentOrder;

public class TtoVo {
    private HsPaymentOrder paymentOrder;
    private Double yy;

    public HsPaymentOrder getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(HsPaymentOrder paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public Double getYy() {
        return yy;
    }

    public void setYy(Double yy) {
        this.yy = yy;
    }
}
