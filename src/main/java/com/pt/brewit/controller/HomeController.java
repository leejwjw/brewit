package com.pt.brewit.controller;


import com.pt.brewit.dto.*;
import com.pt.brewit.security.domain.CustomUser;
import com.pt.brewit.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j // Lombok에서 제공해주는 어노테이션 - 로그 사용하기 위해 클래스 위에 선언
@RequiredArgsConstructor
public class HomeController {

     private final MemberService memberService;
     private final AdminService adminService;
     private final MainService mainService;
     private final EventProductService eventProductService;
    private final ProductService productService;

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
        List<Long> memberCounts = adminService.getMonthlyMemberCount(LocalDate.now().getYear());
        List<Long> orderCounts = adminService.getMonthlyOrderCount(LocalDate.now().getYear(), logged_member);
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

    // 이미지 요청
    @ResponseBody // 데이터 리턴
    @GetMapping("/img/product/{filename}")
    public Resource getImages(@PathVariable("filename") String filename) throws MalformedURLException {
        log.info("GET /product/images - filename : {}", filename);
        log.info("이미지 요청@@@@@@@");
        return new UrlResource("file:" + productService.getFullPath(filename));
    }

    // FAQ 페이지
    @GetMapping("/notice")
    public String notice() { return "main/notice"; }

    @GetMapping("/subscribe")
    public String listSubscriptions(Model model, Pager pager) {
        log.info("GET/Board/list - pager: {}", pager);


        // 페이징 정보 설정
        int total = eventProductService.getTotalCount(pager);

        if (total == 0) {
            // 데이터가 없는 경우 빈 리스트와 PageDTO 설정
            model.addAttribute("subscriptions", Collections.emptyList());
            model.addAttribute("pageDTO", new PageDTO(pager, 0));
        } else {
            // 데이터가 있는 경우 정상 설정
            List<EventProductDTO> subscriptions = eventProductService.getSubscriptions(pager);
            model.addAttribute("subscriptions", subscriptions);
            model.addAttribute("pageDTO", new PageDTO(pager, total));
        }


        // 데이터와 페이징 정보를 모델에 추가
        return "main/subscribeList"; // Thymeleaf 템플릿 이름
    }
}
