package edu.scau.misp.hs.vo;

public class PaymentMethodVo {
    private Integer orderId;
    private Integer paymentMethod;
    private String phonePayment;
    private Double price;
    private Integer discountId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPhonePayment() {
        return phonePayment;
    }

    public void setPhonePayment(String phonePayment) {
        this.phonePayment = phonePayment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }
}
