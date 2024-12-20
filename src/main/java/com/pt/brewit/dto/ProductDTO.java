package com.pt.brewit.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class ProductDTO {
    private MultipartFile file; // 파일 업로드를 위한 필드
    private String filePath;
    private int product_id;
    private int seller_id;
    private String name;
    private int category_id;
    private int subcategory_id;
    private String info;
    private String nation;
    private Integer weight;
    private String is_caffeine;
    private int stock;
    private String attach_name;
    private String attach_origin_name;
    private Integer liked;
    private Date reg_date;
    private Integer regular_price;
    private Integer discount_percent;
    private Integer sale_price;
    private String status; //삭제여부 active, inactive
    private String seller_name;
}