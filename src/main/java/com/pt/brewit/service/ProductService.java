package com.pt.brewit.service;

import com.pt.brewit.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    // 차, 커피, 나머지 베스트순으로 가져오기
    public List<ProductDTO> selectBestProducts(int category_id);




}
