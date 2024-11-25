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
    public List<ProductDTO> getProductsForAll() {
//        List<ProductDTO> products = mainpageMapper.findBestProductsForAll();
//        products.forEach(product -> System.out.println("ProductDTO: " + product.getProduct_id()));
//        return products;
        return mainpageMapper.findBestProductsForAll();
    }

    @Override
    public List<ProductDTO> getProductsForTea() {
        return mainpageMapper.findBestProductsForTea();
    }

    @Override
    public List<ProductDTO> getProductsForCoffee() {
        return mainpageMapper.findBestProductsForCoffee();
    }

    @Override
    public List<ProductDTO> getProductsForAccessories() {
        return mainpageMapper.findBestProductsForCoffee();
    }
}
