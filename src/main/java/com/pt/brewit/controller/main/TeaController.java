package com.pt.brewit.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tea")
public class TeaController {

    @GetMapping
    public String teaBest(Model model) {
        model.addAttribute("name", "상품명1");
        model.addAttribute("sale_price", "123,456");
        return "main/tea";
    }

    @GetMapping("/{subcategory_id}")
    public String teaSubcategory(@PathVariable int subcategory_id) {

        return "main/tea";
    }

}
