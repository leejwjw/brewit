package com.pt.brewit.controller;

import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.service.MainProductService;
import com.pt.brewit.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class HomeEventProductController {
    private MainProductService mainProductService;

    @GetMapping("/eventProducts")
    public String eventProducts(Model model, EventProductDTO eventProductDTO) {

        model.addAttribute("eventProductDTO", eventProductDTO);

        return "main/fragments/subscribe";
    }
}
