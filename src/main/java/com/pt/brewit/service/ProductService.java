package com.pt.brewit.service;

import com.pt.brewit.dto.Pager;
import com.pt.brewit.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    // 차, 커피, 나머지 베스트순으로 가져오기
    public List<ProductDTO> selectBestProducts(int category_id, Pager pager);
    //서브카테고리 가져오기
    public List<ProductDTO> selectProductsBySub(int subcategory_id, Pager pager);
    //카테고리별 상품 전체 개수 조회
    public int getProductCount(int category_id);
    //서브카테고리별 데이터 전체 개수 조회
    public int countProductsBySub(int subcategory_id);

    void registProduct(ProductDTO productDTO);

}
