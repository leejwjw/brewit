package com.pt.brewit.dto;

import lombok.Data;

@Data
public class CountDTO {
    private int member_count;
    private int product_count;
    private int term_event_count;
    private int order_count;
    private int payment_count;
    private int today_count;

}
