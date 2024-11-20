package com.pt.brewit.controller.admin;

import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.dto.SubCategoryDTO;
import com.pt.brewit.mapper.ProductMapper;
import org.springframework.ui.Model;
import com.pt.brewit.dto.CategoryDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.mapper.CategoryMapper;
import com.pt.brewit.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminProductController {
    private final AdminService adminService;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @GetMapping("/registProduct")
    public String registProduct(Model model) {
        List<CategoryDTO> categories = categoryMapper.getAllCategories();
        log.info("categories: {}", categories);
        log.info("registProduct site ap");
        model.addAttribute("categories", categories);
        return "admin/registProduct";
    }

    @PostMapping("/registProduct")
    public String registProduct(@ModelAttribute ProductDTO productDTO, Model model) {

        log.info("registProduct productDTO: {}", productDTO);
        productMapper.insertProduct(productDTO);
        return "redirect:/admin/registProduct";
    }



}
