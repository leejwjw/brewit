package com.pt.brewit.dto;
import lombok.Data;
import lombok.Data;
import java.util.Date;

@Data
public class SellerDTO {
    private int seller_id;        // seller_id
    private long member_id;       // member_id
    private String company_name;   // company_name
    private String b_number;      // b_number
    private Date reg_date;        // reg_date
    private String status;       // status
}
