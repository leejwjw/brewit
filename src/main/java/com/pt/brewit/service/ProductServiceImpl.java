package com.pt.brewit.service;

import com.pt.brewit.dto.Pager;
import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.mapper.AdminMapper;
import com.pt.brewit.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    @Value("${file.dir}")
    private String fileDir; // YAML에서 주입받은 파일 경로

    @Override
    public List<ProductDTO> selectBestProducts(int category_id, Pager pager) {
        return productMapper.selectBestProducts(category_id, pager);
    }

    @Override
    public List<ProductDTO> selectBestProductsSort(int category_id, Pager pager, String sortType) {
        List<ProductDTO> list = null;
        if (sortType.equals("many-review")){
            // 리뷰 많은 순 따로
        }else {
            list = productMapper.selectBestProductsSortByPrice(category_id, pager, sortType);
        }
        return list;
    }

    @Override
    public List<ProductDTO> selectProductsBySub(int subcategory_id, Pager pager) {
        return productMapper.selectProductsBySub(subcategory_id, pager);
    }

    @Override   //정렬
    public List<ProductDTO> selectProductsBySubSort(int subcategory_id, Pager pager, String sortType) {
        List<ProductDTO> list = null;
        if(sortType.equals("many-review")){
            //  리뷰 많은 순 따로
        }else{
            list = productMapper.selectProductsBySubSort(subcategory_id, pager, sortType);
        }
        return list;
    }

    @Override
    public int getProductCount(int category_id) {
        Long count = productMapper.countAllProducts(category_id);
        return count.intValue();
    }

    @Override
    public int countProductsBySub(int subcategory_id) {
        Long count = productMapper.countProductsBySub(subcategory_id);
        return count.intValue();
    }
    @Override
    public ProductDTO getProductById(int id) {
        ProductDTO product = productMapper.selectProductById(id);
        String filePath = getFullPath(product.getAttach_name());
        product.setFilePath(filePath); // 파일 경로를 DTO에 추가
       return product;
    }

    @Override
    public void registProduct(ProductDTO productDTO) {
        // 상품 등록 로직
        productMapper.insertProduct(productDTO);
    }
    @Override
    public  void updateProduct(int id, ProductDTO productDTO) {
        if(productDTO.getIs_caffeine() == null) {
            productDTO.setIs_caffeine("false");
        }
        productMapper .updateProductValue(id, productDTO);
    }
    @Override
    public  void deleteProduct(int id, ProductDTO productDTO) {

        productMapper .deleteProductOne(id, productDTO);
    }
    @Override
    public List<ProductDTO> selectAllProducts() {
        List<ProductDTO> products = productMapper.selectAllProducts();
        // 파일 경로를 추가로 처리할 수 있는 로직
        for (ProductDTO product : products) {
            String file = getFullPath(product.getAttach_name());
            product.setFilePath(file); // 파일 경로를 DTO에 추가
        }
        return products;
    }

    // 파일 전체 경로 리턴
    @Override
    public String getFullPath(String filename) {
        return fileDir + '/' +filename;
    }
//    private String getFilePath(String attachName) {
//        int index = fileDir.indexOf("/img");
//        if (index != -1) {
//            String result = fileDir.substring(index);
//            return result + "/" +  attachName;
//        } else {
//            System.out.println("'/img'가 경로에 없습니다.");
//            return "";
//        }
//
//    }

}
