package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.Pager;
import com.pt.brewit.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    // 차, 커피, 나머지 베스트순으로 가져오기
    public List<ProductDTO> selectBestProducts(int category_id, Pager pager);
    public List<ProductDTO> selectBestProductsSort(int category_id, Pager pager, String sortType);
    //서브카테고리 가져오기
    public List<ProductDTO> selectProductsBySub(int subcategory_id, Pager pager);
    public List<ProductDTO> selectProductsBySubSort(int subcategory_id, Pager pager, String sortType);
    //카테고리별 상품 전체 개수 조회
    public int getProductCount(int category_id);
    //서브카테고리별 데이터 전체 개수 조회
    public int countProductsBySub(int subcategory_id);
    //상품정보 전체 조회
    public List<ProductDTO> selectAllProducts();
    //상품 등록
    void registProduct(ProductDTO productDTO);
    //상품 조회
    public ProductDTO getProductById(int id);
    // 상품 수정
    public void updateProduct(int id, ProductDTO productDTO);
    //상품 삭제
    public void deleteProduct(int id, ProductDTO productDTO);
    //파일 조회
    public String getFullPath(String filename);
}
