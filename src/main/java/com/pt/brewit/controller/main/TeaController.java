package com.pt.brewit.controller.main;

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

    @GetMapping
    public String teaBest(Model model) {
        //tea : 1 / best : liked순 /
        List<ProductDTO> teaList = productService.selectBestProducts(1);
        model.addAttribute("products", teaList);
        return "main/tea";
    }

    @GetMapping("/{subcategory_id}")
    public String teaSubcategory(@PathVariable int subcategory_id) {
        return "main/tea";
    }


}
