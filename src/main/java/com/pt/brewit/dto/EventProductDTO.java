package com.pt.brewit.dto;

import lombok.Data;

@Data
public class EventProductDTO {

    private int term_item_id;
    private String product_id;
    private int term;
    private int seller_id;
    private String event_name;
    private String event_info;
    private String attach_name;
    private String attach_origin_name;
    private int discount;
    private int price;
    private String status;
}
