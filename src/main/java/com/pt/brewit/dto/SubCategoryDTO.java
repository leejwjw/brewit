package com.pt.brewit.dto;

import lombok.Data;


@Data
public class SubCategoryDTO {
    private int subcategory_id;         // 중분류 아이디
    private int category_id;            // 대분류 아이디
    private String subcategory_name;    // 중분류 이름
}
