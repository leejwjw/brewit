package com.pt.brewit.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EventProductDTO {
    private MultipartFile file; // 파일 업로드를 위한 필드
    private String filePath;

    private int term_item_id;
    private String product_id;
    private int term;
    private int seller_id;
    private String subtitle;
    private String event_name;
    private String event_info;
    private String attach_info;
    private String attach_origin_info;
    private String attach_name;
    private String attach_origin_name;
    private int discount;
    private int sale_price;
    private int regular_price;
    private String status;
    private int stock;
}
