package com.pt.brewit.dto;

import lombok.Data;


@Data
public class CategoryDTO {
    private int category_id;         // 대분류 아이디
    private String name;            // 대분류 이름
}
