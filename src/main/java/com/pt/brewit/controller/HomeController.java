package com.pt.brewit.controller;


import com.pt.brewit.dto.CountDTO;
import com.pt.brewit.mapper.AdminMapper;
import com.pt.brewit.security.domain.CustomUser;
import com.pt.brewit.service.AdminService;
import com.pt.brewit.service.MemberService;
import com.pt.brewit.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j // Lombok에서 제공해주는 어노테이션 - 로그 사용하기 위해 클래스 위에 선언
@RequiredArgsConstructor
public class HomeController {

     private final MemberService memberService;
     private final AdminService adminService;
    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUser user, Model model) {
        log.info("home");
        log.info("user: {}", user);

        if(user != null) {
            model.addAttribute("member", user.getMember()); // MemberDTO화면에 전달
        }
        return "main/mainPage";

    }

    @GetMapping("/admin")
    public String admin(Model model) {
        int members = adminService.getMemberCount();
        int products = adminService.getProductCount();
        int term_events = adminService.getTermEventCount();
        int orders = adminService.getOrderCount();
        int payments = adminService.getPaymentCount();
        int todays = adminService.getTodayCount();
        CountDTO countDTO = new CountDTO();
        countDTO.setMember_count(members);
        countDTO.setProduct_count(products);
        countDTO.setTerm_event_count(term_events);
        countDTO.setOrder_count(orders);
        countDTO.setPayment_count(payments);
        countDTO.setToday_count(todays);
        model.addAttribute("count", countDTO);

        return "admin/index";
    }

    // FAQ 페이지
    @GetMapping("/notice")
    public String notice() { return "main/notice"; }


}
