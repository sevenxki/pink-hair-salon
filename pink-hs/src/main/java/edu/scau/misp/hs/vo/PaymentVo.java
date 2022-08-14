package edu.scau.misp.hs.vo;

import edu.scau.misp.hs.entity.HsPaymentOrder;
import org.springframework.security.core.parameters.P;

import java.util.Date;

public class PaymentVo {

   private Integer orderId;
   private Integer discountId;

   public Integer getOrderId() {
      return orderId;
   }

   public void setOrderId(Integer orderId) {
      this.orderId = orderId;
   }

   public Integer getDiscountId() {
      return discountId;
   }

   public void setDiscountId(Integer discountId) {
      this.discountId = discountId;
   }
}
