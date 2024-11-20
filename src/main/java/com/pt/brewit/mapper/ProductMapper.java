package com.pt.brewit.mapper;

import com.pt.brewit.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    // 상품 베스트순으로 가져오기
    public List<ProductDTO> selectBestProducts(int category_id);







}
