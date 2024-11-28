package com.pt.brewit.mapper;

import com.pt.brewit.dto.AttachmentDTO;
import com.pt.brewit.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainMapper {

    // BEST 상품 가져오기
    List<ProductDTO> findBestProducts();

    // 해당 Id값에 해당하는 정보 가져오기
    List<ProductDTO> findProductsByCategory(@Param("category_id") int category_id);

    // Toll 상품 리스트 가져오기
    List<ProductDTO> findProductsByTool();

    // 메인 이미지 가져오기
    List<AttachmentDTO> findMainImg();



}
