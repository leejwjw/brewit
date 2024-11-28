package com.pt.brewit.controller;

import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.service.EventProductService;
import com.pt.brewit.service.MainProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class HomeProductController {
    private final MainProductService mainProductService;
    private final EventProductService eventProductService;

    @GetMapping("/products")
    public String getProducts(@RequestParam String tab_id, Model model) {
        List<ProductDTO> products;
        try {
            switch (tab_id) {
                case "best":
                    products = mainProductService.getBestProducts();
                    log.info("productBest: {}", products);
                    log.info("best_tab_id: {}", tab_id);
                    break;
                case "tea":
                    products = mainProductService.getProductsByCategory(1);
                    log.info("productTea: {}", products);
                    log.info("tea_tab_id: {}", tab_id);
                    break;
                case "coffee":
                    products = mainProductService.getProductsByCategory(2);
                    log.info("productCoffee: {}", products);
                    log.info("coffee_tab_id: {}", tab_id);
                    break;
                case "tool":
                    products = mainProductService.getProductsByTool();
                    log.info("productTool: {}", products);
                    log.info("tool_tab_id: {}", tab_id);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid tab_id: " + tab_id);
            }
        } catch (Exception e) {
            log.error("Error fetching products for tab_id: {}", tab_id, e);
            throw new RuntimeException("Error fetching products", e);
        }
        model.addAttribute("products", products);
        model.addAttribute("tab_id", tab_id);
        return "main/fragments/productList";
    }

    // 구독 상품 상세페이지 연결
    @GetMapping("/product/{term_item_id}")
    public String getProduct(@PathVariable("term_item_id") int term_item_id, Model model) {
        EventProductDTO product = eventProductService.getFindProductId(term_item_id);
        log.info("findProductId: {}", product);
        model.addAttribute("product", product);
        return "main/productSubsDetail";
    }


}
