package com.pt.brewit.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home() {

        return "../static/main/index";
    }
    @GetMapping("/admin")
    public String admin() {
        return "../static/admin/html/index";
    }
}
