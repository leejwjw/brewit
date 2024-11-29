package com.pt.brewit.controller;


import com.pt.brewit.dto.AttachmentDTO;
import com.pt.brewit.dto.CountDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.ChartDTO;
import com.pt.brewit.mapper.MemberMapper;
import com.pt.brewit.security.domain.CustomUser;
import com.pt.brewit.service.AdminService;
import com.pt.brewit.service.MainService;
import com.pt.brewit.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j // Lombok에서 제공해주는 어노테이션 - 로그 사용하기 위해 클래스 위에 선언
@RequiredArgsConstructor
public class HomeController {

     private final MemberService memberService;
     private final AdminService adminService;
     private final MainService mainService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUser user, Model model) {
        log.info("home");
        log.info("user: {}", user);

        if(user != null) {
            model.addAttribute("member", user.getMember()); // MemberDTO화면에 전달
        }

        // 메인 이미지 요청
        List<AttachmentDTO> mainImg = mainService.getMainImg();
        model.addAttribute("mainImg", mainImg);
        log.info("mainImg: {}", mainImg);
        return "main/mainPage";

    }

    @GetMapping("/admin")
    public String admin(Model model, @AuthenticationPrincipal CustomUser user) {
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

        MemberDTO logged_member = memberService.getMember(user.getUsername());

        // 해당 회원의 월별 가입 수 가져오기
        List<Long> memberCounts = adminService.getMonthlyMemberCount(2024);
        List<Long> orderCounts = adminService.getMonthlyOrderCount(2024, logged_member);
        List<Long> termEventCounts = adminService.getMonthlyTermEventCount(2024, logged_member);

        model.addAttribute("count", countDTO);


        model.addAttribute("memberCounts", memberCounts);
        model.addAttribute("orderCounts", orderCounts);
        model.addAttribute("termEventCounts", termEventCounts);
        model.addAttribute("user", logged_member);
        log.info("memberCounts: {}", memberCounts);
        log.info("orderCounts: {}", orderCounts);
        log.info("termEventCounts: {}", termEventCounts);
        return "admin/index";
    }

    // FAQ 페이지
    @GetMapping("/notice")
    public String notice() { return "main/notice"; }


}
