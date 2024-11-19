package com.pt.brewit.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home() {
        log.info("home");
        return "main/mainPage";
    }


    @GetMapping("/admin")
    public String admin() {
        return "../static/admin/html/index";
    }

    //tea 상품페이지
    //Test
    //Test22
    @GetMapping("/details")
    public String details(Model model) {
        model.addAttribute("name", "상품명1");
        model.addAttribute("sale_price", "123,456");

        return "main/productExample";
    }
}
