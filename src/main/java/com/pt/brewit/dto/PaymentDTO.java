package com.pt.brewit.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PaymentDTO {
    private int payment_id;          // 결제 ID
    private String order_num;        // 주문 번호
    private int amount;              // 결제 금액
    private String status;           // 결제 상태 (complete, refunded)
    private Date payment_date;       // 결제 날짜
    private Date cancel_date;        // 취소 날짜 (nullable)
    private String method;    // 결제 방법
    private String product_name;
    private String pay_member_name;
    private String price;



}
