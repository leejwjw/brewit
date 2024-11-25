package com.pt.brewit.controller.main;

import com.pt.brewit.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j // Lombok에서 제공해주는 어노테이션 - 로그 사용하기 위해 클래스 위에 선언
@RequiredArgsConstructor
public class MainController {
    // 로그인 폼 요청
    @GetMapping("/login")
    public String login(@ModelAttribute("member") MemberDTO member) {
        log.info("login form!!!!!");
        return "main/login";
    }
}
