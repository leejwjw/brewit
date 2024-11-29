package com.pt.brewit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.service.EventProductService;
import com.pt.brewit.service.MainService;
import com.pt.brewit.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/products")
public class HomeProductController {
    private final MainService mainService;
    private final EventProductService eventProductService;
    private final ProductService productService;


    @GetMapping("")
    public String getProducts(@RequestParam String tab_id, Model model) {
        List<ProductDTO> products;
        try {
            switch (tab_id) {
                case "best":
                    products = mainService.getBestProducts();
                    log.info("productBest: {}", products);
                    log.info("best_tab_id: {}", tab_id);
                    break;
                case "tea":
                    products = mainService.getProductsByCategory(1);
                    log.info("productTea: {}", products);
                    log.info("tea_tab_id: {}", tab_id);
                    break;
                case "coffee":
                    products = mainService.getProductsByCategory(2);
                    log.info("productCoffee: {}", products);
                    log.info("coffee_tab_id: {}", tab_id);
                    break;
                case "tool":
                    products = mainService.getProductsByTool();
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
    @GetMapping("/{term_item_id}")
    public String getProduct(@PathVariable("term_item_id") int term_item_id, Model model) {
        EventProductDTO product = eventProductService.getFindProductId(term_item_id);
        log.info("findProductId: {}", product);
        model.addAttribute("product", product);
        return "main/productSubsDetail";
    }

    // 쿠키에 저장된 데이터 꺼내기
    @GetMapping("/order/singlePayment")
    public String singlePaymentPage(@CookieValue(value = "productData", defaultValue = "") String productData, Model model) {

        // 쿠키 값 "productData"를 읽어옵니다. 기본값은 빈 문자열("")로 설정됩니다.
        if (!productData.isEmpty()) {
            // 쿠키 데이터가 비어있지 않은 경우 JSON 문자열을 파싱합니다.
            ObjectMapper objectMapper = new ObjectMapper(); // JSON 문자열을 Java 객체로 변환하기 위한 Jackson 라이브러리 객체 생성.
            try {
                // JSON 문자열을 Java Map 형태로 변환.
                Map<String, Object> productMap = objectMapper.readValue(productData, new TypeReference<>() {});
                // 할인 금액 계산 (정가 - 할인가)
                String regularPriceStr = ((String) productMap.get("regular_price")).replaceAll("[^0-9]", ""); // 숫자만 남김
                String salePriceStr = ((String) productMap.get("sale_price")).replaceAll("[^0-9]", "");
                int regularPrice = Integer.parseInt(regularPriceStr);
                int salePrice = Integer.parseInt(salePriceStr);
                // 할인 금액 계산
                int discountPrice = regularPrice - salePrice;

                // 계산된 할인 금액을 추가
                productMap.put("discount_price", discountPrice);
                // logo 찍어서 데이터 확인
                log.info("productMap: {}", productMap);
                // 변환된 데이터를 뷰로 전달하기 위해 모델에 추가.
                model.addAttribute("productData", productMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace(); // JSON 파싱 오류가 발생하면 예외를 출력.
            }
        }
        // singlePayment.html 뷰를 반환하여 해당 페이지를 렌더링.
        return "main/singlePayment";
    }

}
