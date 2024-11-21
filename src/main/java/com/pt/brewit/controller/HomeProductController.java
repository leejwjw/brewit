package com.pt.brewit.controller;

import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.service.MainProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class HomeProductController {
    private final MainProductService mainProductService;

    //  인기 상품을 All / 차 / 커피 / 관련용품 등 눌러서 표기하는 프로그램 영역.
    @GetMapping("/products")
    public List<ProductDTO> getProducts(@RequestParam String tab_id) {
        switch (tab_id) {
            case "best":
                return mainProductService.getBestProducts();
            case "tea":
                return mainProductService.getProductsByCategory(1);
            case "coffee":
                return mainProductService.getProductsByCategory(2);
            case "tool":
                return mainProductService.getProductsByTool();
            default:
                throw new IllegalArgumentException("탭 이름이 잘못되었습니다.");
        }
    }
}
