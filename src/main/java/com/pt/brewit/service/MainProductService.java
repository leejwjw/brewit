package com.pt.brewit.service;

import com.pt.brewit.dto.ProductDTO;

import java.util.List;

public interface MainProductService {

    List<ProductDTO> getBestProducts();
    List<ProductDTO> getProductsByCategory(int category_id);
    List<ProductDTO> getProductsByTool();
}
