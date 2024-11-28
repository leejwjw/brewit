package com.pt.brewit.service;

import com.pt.brewit.dto.AttachmentDTO;
import com.pt.brewit.dto.ProductDTO;

import java.util.List;

public interface MainService {

    // BEST 상품 가져오기
    List<ProductDTO> getBestProducts();

    // 해당 Id값에 해당하는 정보 가져오기
    List<ProductDTO> getProductsByCategory(int category_id);

    // Toll 상품 리스트 가져오기
    List<ProductDTO> getProductsByTool();

    // 메인 이미지 가져오기
    List<AttachmentDTO> getMainImg();





}
