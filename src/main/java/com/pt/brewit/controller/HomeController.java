package com.pt.brewit.controller;


import com.pt.brewit.security.domain.CustomUser;
import com.pt.brewit.service.MemberService;
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
    public String admin() {
        return "admin/index";
    }


}
