package com.pt.brewit.controller.admin;


import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.ProductDTO;



import com.pt.brewit.mapper.MemberMapper;
import com.pt.brewit.security.domain.CustomUser;
import com.pt.brewit.service.EventProductService;
import com.pt.brewit.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminTermController {
    private final ProductService productService;
    private final EventProductService eventProductService;
    private final MemberMapper memberMapper;

    @Value("${file.dir}")
    private String fileDir;



    @GetMapping("/termRegistThree")
    public String termRegist(Model model, @AuthenticationPrincipal CustomUser user) {
        MemberDTO logged_member = memberMapper.selectMemberByUsername(user.getUsername());
        List<ProductDTO> products = productService.selectProductsByUserId(logged_member.getMember_id());


        log.info( "log !!!!" + user);
        log.info( "productsproductsproductsproducts !!!!" + products);
        model.addAttribute("products", products);

        return "admin/termRegistThree";
    }

    @PostMapping("/termRegistThree")
    public String registThreeEvent(@ModelAttribute EventProductDTO eventProductDTO, Model model, @AuthenticationPrincipal CustomUser user) {

        MemberDTO logged_member = memberMapper.selectMemberByUsername(user.getUsername());

        // 파일 저장 로직
        MultipartFile file = eventProductDTO.getFile(); // ProductDTO에서 파일 가져오기
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
                eventProductDTO.setAttach_name(saveFilename); // 파일 이름을 DTO에 저장

            } catch (IOException e) {
                return "admin/termRegistThree"; // 오류 발생 시 다시 폼으로 돌아감
            }
        }
        eventProductDTO.setTerm(3);
        eventProductDTO.setSeller_id(logged_member.getMember_id());
        log.info("eventProductDTOeventProductDTOeventProductDTOeventProductDTOeventProductDTOeventProductDTO : {}", eventProductDTO);
        eventProductService.insertEventProduct(eventProductDTO);
        return "redirect:/admin/termProducts";
    }

    @GetMapping("/termRegistYear")
    public String termRegistYear(Model model, @AuthenticationPrincipal CustomUser user) {
        MemberDTO logged_member = memberMapper.selectMemberByUsername(user.getUsername());
        List<ProductDTO> products = productService.selectProductsByUserId(logged_member.getMember_id());
        model.addAttribute("products", products);

        return "admin/termRegistYear";
    }

    @PostMapping("/termRegistYear")
    public String registYearEvent(@ModelAttribute EventProductDTO eventProductDTO, Model model, @AuthenticationPrincipal CustomUser user) {

        MemberDTO logged_member = memberMapper.selectMemberByUsername(user.getUsername());
        // 파일 저장 로직
        MultipartFile file = eventProductDTO.getFile(); // ProductDTO에서 파일 가져오기
        log.info("file@@dddd@@@: {}", file);
        if (file != null && !file.isEmpty()) {
            String orgFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String ext = orgFilename.substring(orgFilename.lastIndexOf(".")); // .png
            String saveFilename = uuid + ext; // 저장할 파일명
            File destinationFile = new File(fileDir, saveFilename);
            try {
                log.info("성공");
                // 파일 저장-->
                file.transferTo(destinationFile);
                eventProductDTO.setAttach_name(saveFilename); // 파일 이름을 DTO에 저장

            } catch (IOException e) {
                log.info("실패");
                return "admin/termRegistYear"; // 오류 발생 시 다시 폼으로 돌아감
            }
        }
        eventProductDTO.setTerm(12);
        eventProductDTO.setSeller_id(logged_member.getMember_id());
        eventProductService.insertEventProduct(eventProductDTO);
        return "redirect:/admin/termRegistYear";
    }


    @GetMapping("/termProducts")
    public String termProducts(Model model, @AuthenticationPrincipal CustomUser user) {
        // CustomUser에서 필요한 정보를 추출
        String username = user.getUsername();
        MemberDTO logged_member = memberMapper.selectMemberByUsername(username);
        List<EventProductDTO> products = eventProductService.getEventProductList(logged_member);
        log.info("%%%%%%%%%%%%%%productDTO : {}", products);
        model.addAttribute("products", products);
        return "admin/termProducts";
    }

    //삭제 요청 처리
    @PostMapping("termProducts/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, @ModelAttribute ProductDTO product) {
        eventProductService.deleteProduct(id);
        return "redirect:/admin/termProducts";
    }


}
