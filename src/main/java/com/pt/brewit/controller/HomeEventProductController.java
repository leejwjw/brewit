package com.pt.brewit.controller;


import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.service.EventProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class HomeEventProductController {

    private final EventProductService eventProductService;

    @GetMapping("/eventProducts")
    public List<EventProductDTO> getAllEventProducts() {
        return eventProductService.getEventProducts();
    }

}
