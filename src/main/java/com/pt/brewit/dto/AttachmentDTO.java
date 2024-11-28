package com.pt.brewit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AttachmentDTO {
    private int attach_id;
    private int member_id;
    private String name;
    private String origin_name;
    private String type;
    private String mime;
    private Date reg_date;
    private String status;
}
