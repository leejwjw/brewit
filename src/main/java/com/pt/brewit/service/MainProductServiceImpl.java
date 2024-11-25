package com.pt.brewit.service;

import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.mapper.MainpageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainProductServiceImpl implements MainProductService{


    private final MainpageMapper mainpageMapper;

    @Override
    public List<ProductDTO> getBestProducts() {
        return mainpageMapper.findBestProducts();
    }

    @Override
    public List<ProductDTO> getProductsByCategory(int category_id) {
        return mainpageMapper.findProductsByCategory(category_id);
    }

    @Override
    public List<ProductDTO> getProductsByTool() {
        return mainpageMapper.findProductsByTool();
    }
}
