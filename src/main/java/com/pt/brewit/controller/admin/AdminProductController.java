package com.pt.brewit.controller.admin;

import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.dto.SubCategoryDTO;
import com.pt.brewit.mapper.MemberMapper;
import com.pt.brewit.mapper.ProductMapper;
import com.pt.brewit.security.domain.CustomUser;
import com.pt.brewit.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor

public class AdminProductController {
    private final AdminService adminService;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;
    private final ProductService productService;
    private final MemberMapper memberMapper;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/admin/registProduct")
    public String registProduct(Model model) {
        List<CategoryDTO> categories = categoryMapper.getAllCategories();
        log.info("categories: {}", categories);
        log.info("registProduct site ap");
        model.addAttribute("categories", categories);
        return "admin/registProduct";
    }

    @PostMapping("/admin/registProduct")
    public String registProduct(@ModelAttribute ProductDTO productDTO, Model model, @AuthenticationPrincipal CustomUser user) {
        // 파일 저장 로직

        MultipartFile file = productDTO.getFile(); // ProductDTO에서 파일 가져오기
        log.info("file@@@@@: {}", file);
        log.info("user@@@@@: {}", user);

        String username = user.getUsername();
        MemberDTO logged_member = memberMapper.selectMemberByUsername(username);

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
        if(productDTO.getIs_caffeine() == null) {
            productDTO.setIs_caffeine("false");
        }
        log.info("product!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! :  {}",productDTO );
        productDTO.setSeller_id(logged_member.getMember_id());
        productMapper.insertProduct(productDTO); // DB에 제품 정보 저장
        return "redirect:/admin/products";
    }


    @GetMapping("/admin/products")
    public String showProducts(Model model) {
        log.info("products site open !");
        List<ProductDTO> products = productService.selectAllProducts();
        model.addAttribute("products", products);
        log.info("product array!!!! :{}", products);
        return "admin/products";
    }
    // 이미지 요청
    @ResponseBody // 데이터 리턴
    @GetMapping("/admin/img/product/{filename}")
    public Resource getImages(@PathVariable("filename") String filename) throws MalformedURLException {
        log.info("GET /product/images - filename : {}", filename);
        log.info("이미지 요청@@@@@@@");
        return new UrlResource("file:" + productService.getFullPath(filename));
    }


    //수정페이지
    @GetMapping("/admin/products/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        log.info("id값---> :{}",id);
        ProductDTO product = productService.getProductById(id);
        List<CategoryDTO> categories = categoryMapper.getAllCategories();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "admin/editProduct";
    }
    //수정 요청
    @PostMapping("/admin/products/editProduct/{id}")
    public String updateProduct(@PathVariable("id") int id, @ModelAttribute ProductDTO product) {
        productService.updateProduct(id, product); // 서비스 레이어에서  정보 업데이트
        return "redirect:/admin/products"; // 수정 후 회원 목록 페이지로 리다이렉트
    }
    //삭제 요청 처리
    @PostMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, @ModelAttribute ProductDTO product) {
        productService.deleteProduct(id, product);
        return "redirect:/admin/products";
    }


}
