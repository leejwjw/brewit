package com.pt.brewit.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO {
    private int order_num;          // 주문 번호
    private int product_id;         // 제품 ID
    private Integer member_id;      // 회원 ID (nullable)
    private Integer seller_id;      // 판매자 ID (nullable)
    private int amount;            // 수량
    private BigDecimal price;      // 가격
    private String is_subscribe;    // 구독 여부
    private Date cancel_date;       // 취소 날짜 (nullable)
    private Date reg_date;          // 등록 날짜
    private String status;    // 주문 상태
    private String product_name; //상품이름
    private String attach_name; // 파일첨부파일 저장 이름
    private String buyer_name; // 구매자 이름
    private String seller_name; // 판매자 이름
}