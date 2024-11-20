package com.pt.brewit.service;

import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.mapper.AdminMapper;
import com.pt.brewit.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> selectBestProducts(int category_id) {
        return productMapper.selectBestProducts(category_id);
    }
    @Override
    public void registProduct(ProductDTO productDTO) {
        // 상품 등록 로직
        productMapper.insertProduct(productDTO);
    }

}
