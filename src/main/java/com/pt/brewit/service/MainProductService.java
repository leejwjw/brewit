package com.pt.brewit.service;

import com.pt.brewit.dto.ProductDTO;

import java.util.List;

public interface MainProductService {

    List<ProductDTO> getProductsForAll();
    List<ProductDTO> getProductsForTea();
    List<ProductDTO> getProductsForCoffee();
    List<ProductDTO> getProductsForAccessories();
}
