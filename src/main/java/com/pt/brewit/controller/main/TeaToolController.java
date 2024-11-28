package com.pt.brewit.controller.main;

import com.pt.brewit.dto.PageDTO;
import com.pt.brewit.dto.Pager;
import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/teatool")
@RequiredArgsConstructor
@Slf4j
public class TeaToolController {

    private final ProductService productService;

    // 도구 통합 요청
    @GetMapping("/{subcategory_id}")
    public String toolSubcategory(@PathVariable int subcategory_id, Model model, Pager pager, String sortType) {
        List<ProductDTO> teaToolSub = null;
        log.info("sort type : {}", sortType);
        if(sortType != null) { // 정렬 누름
            if(subcategory_id == 0) {
                teaToolSub = productService.selectBestProductsSort(3, pager, sortType);
                model.addAttribute("pageDTO", new PageDTO(pager, productService.getProductCount(3)));
                model.addAttribute("subcategory_id", 0);
            }else {
                teaToolSub = productService.selectProductsBySubSort(subcategory_id, pager, sortType);
                model.addAttribute("pageDTO", new PageDTO(pager, productService.countProductsBySub(subcategory_id)));
                model.addAttribute("subcategory_id", subcategory_id);
            }
        }else { // 정렬 안 누름
            if(subcategory_id == 0) {  //teatool : 3 / best : liked desc 순
                teaToolSub = productService.selectBestProducts(3, pager);
                model.addAttribute("pageDTO", new PageDTO(pager, productService.getProductCount(3))); // 카테고리에 맞는 데이터 중 전체 개수 total
                model.addAttribute("subcategory_id", 0);
            }else { // 서브카테고리
                teaToolSub = productService.selectProductsBySub(subcategory_id, pager);
                model.addAttribute("pageDTO", new PageDTO(pager, productService.countProductsBySub(subcategory_id))); // 서브카테고리에 맞는 데이터 전체개수 total
                model.addAttribute("subcategory_id", subcategory_id);
            }

        }
        // 상품 목록 보내기
        model.addAttribute("products", teaToolSub);
        return "main/teatool";
    }

    // 차(Tea)용품 상세 페이지 호출
    @GetMapping("/{subcategory_id}/{product_id}")
    public String productDetail(@PathVariable("subcategory_id") int subcategory_id, @PathVariable("product_id") int product_id, Model model) {
        ProductDTO productDetail = productService.getProductById(product_id);
        log.info("product:{}", productDetail);
        model.addAttribute("productDetail", productDetail);
        return "main/productDetail";
    }


}
