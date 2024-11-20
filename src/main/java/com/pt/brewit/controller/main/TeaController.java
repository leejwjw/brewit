package com.pt.brewit.controller.main;

import com.pt.brewit.dto.PageDTO;
import com.pt.brewit.dto.Pager;
import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tea")
@RequiredArgsConstructor
public class TeaController {

    private final ProductService productService;
    //Tea Best
    @GetMapping
    public String teaBest(Model model, Pager pager) {
        //tea : 1 / best : liked desc 순
        List<ProductDTO> teaList = productService.selectBestProducts(1, pager);
        model.addAttribute("products", teaList);
        // 카테고리에 맞는 데이터 중 전체 개수 total
        model.addAttribute("pageDTO", new PageDTO(pager, productService.getProductCount(1)));

        return "main/tea";
    }
    //차 서브카테고리
    @GetMapping("/{subcategory_id}")
    public String teaSubcategory(@PathVariable int subcategory_id, Model model, Pager pager) {
        List<ProductDTO> teaSub = productService.selectProductsBySub(subcategory_id, pager);
        model.addAttribute("products", teaSub);
        // 서브카테고리에 맞는 데이터 전체개수 total
        model.addAttribute("pageDTO", new PageDTO(pager, productService.countProductsBySub(subcategory_id)));
        return "main/tea";
    }


}
