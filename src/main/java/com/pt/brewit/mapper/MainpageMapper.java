package com.pt.brewit.mapper;

import com.pt.brewit.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainpageMapper {

    List<ProductDTO> findBestProductsForAll();
    List<ProductDTO> findBestProductsForTea();
    List<ProductDTO> findBestProductsForCoffee();
    List<ProductDTO> findBestProductsForAccessories();

}
