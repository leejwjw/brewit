package com.pt.brewit.mapper;

import com.pt.brewit.dto.Pager;
import com.pt.brewit.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProductMapper {

    // 상품 베스트순으로 가져오기
    public List<ProductDTO> selectBestProducts(int category_id);
    // 상품 등록
    void insertProduct(ProductDTO productDTO);
    //상품 베스트순으로 가져오기
    public List<ProductDTO> selectBestProducts(@Param("category_id")int category_id, @Param("pager") Pager pager);
    //서브카테고리 가져오기
    public List<ProductDTO> selectProductsBySub(@Param("subcategory_id") int subcategory_id,@Param("pager") Pager pager);
    //카테고리별 상품 전체 개수 조회
    Long countAllProducts(int category_id);
    //서브카테고리별 데이터 전체 개수 조회
    Long countProductsBySub(int subcategory_id);
    //베스트순 상품 정렬
    List<ProductDTO> selectBestProductsSortByPrice(@Param("category_id")int category_id, @Param("pager") Pager pager, @Param("sortType") String sortType);
    //서브 상품 정렬
    List<ProductDTO> selectProductsBySubSort(@Param("subcategory_id") int subcategory_id,@Param("pager") Pager pager, @Param("sortType") String sortType);
    // 상품 전체 조회
    public List<ProductDTO> selectAllProducts();
    // 상품 하나 조회
    public  ProductDTO selectProductById(int id);
    // 상품 수정
    public void updateProductValue(@Param("id") int id, @Param("product") ProductDTO product);
}