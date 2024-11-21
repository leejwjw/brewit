package com.pt.brewit.controller.admin;

import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.dto.SubCategoryDTO;
import com.pt.brewit.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import com.pt.brewit.dto.CategoryDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.mapper.CategoryMapper;
import com.pt.brewit.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminProductController {
    private final AdminService adminService;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Value("${file.dir}")
    private String fileDir;

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

        log.info("productDTO !!!!!!!!!!: {}", productDTO);
        // 파일 저장 로직

        MultipartFile file = productDTO.getFile(); // ProductDTO에서 파일 가져오기
        log.info("file@@@@@: {}", file);

        if (file != null && !file.isEmpty()) {
            String orgFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String ext = orgFilename.substring(orgFilename.lastIndexOf(".")); // .png
            String saveFilename = uuid + ext; // 저장할 파일명
            File destinationFile = new File(fileDir, saveFilename);
            try {
                // 파일 저장-->
                file.transferTo(destinationFile);
                productDTO.setAttach_name(saveFilename); // 파일 이름을 DTO에 저장

            } catch (IOException e) {
                log.error("파일 저장 중 오류 발생: {}", e.getMessage());
                // 오류 처리 로직 추가 (예: 모델에 오류 메시지 추가)
                model.addAttribute("errorMessage", "파일 저장 중 오류가 발생했습니다.");
                return "admin/registProduct"; // 오류 발생 시 다시 폼으로 돌아감
            }
        }
        if (!productDTO.is_caffeine()) {
            productDTO.set_caffeine(false);
        }
        productMapper.insertProduct(productDTO); // DB에 제품 정보 저장
        return "redirect:/admin/registProduct"; // 성공적으로 등록 후 리다이렉트
    }



}
