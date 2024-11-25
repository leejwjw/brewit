package com.pt.brewit.mapper;

import com.pt.brewit.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainpageMapper {

    List<ProductDTO> findBestProducts();
    List<ProductDTO> findProductsByCategory(@Param("category_id") int category_id);
    List<ProductDTO> findProductsByTool();

}
