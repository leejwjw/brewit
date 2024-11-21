package com.pt.brewit.service;

import com.pt.brewit.dto.Pager;
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
    public List<ProductDTO> selectBestProducts(int category_id, Pager pager) {
        return productMapper.selectBestProducts(category_id, pager);
    }

    @Override
    public List<ProductDTO> selectProductsBySub(int subcategory_id, Pager pager) {
        return productMapper.selectProductsBySub(subcategory_id, pager);
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
    public void registProduct(ProductDTO productDTO) {
        // 상품 등록 로직
        productMapper.insertProduct(productDTO);
    }

}