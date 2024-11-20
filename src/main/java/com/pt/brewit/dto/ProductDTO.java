package com.pt.brewit.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ProductDTO {
    private int product_id;
    private int seller_id;
    private String name;
    private int category_id;
    private int subcategory_id;
    private String info;
    private String nation;
    private Integer weight;
    private boolean is_caffeine;
    private int stock;
    private String attach_id;
    private Integer liked;
    private Date reg_date;
    private Integer regular_price;
    private Integer discount_price;
    private Integer sale_price;
}