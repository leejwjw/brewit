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
    private int mainsubcategory_id;

    // 메인에서 subcategory_id 를 사용하는데 다른곳의 subcategory_id를 바라봐서 
    // 별도의 변수 선언하고 subcategory_id 주입받아서 사용
    public int getMainsubcategory_id() {
        return this.mainsubcategory_id = subcategory_id;
    }
}