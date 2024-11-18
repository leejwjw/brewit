package com.pt.brewit.controller.main;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@Slf4j
public class MIainController {

    // 로그인 폼 요청
    @GetMapping("/login")
    public String login() {
        log.info("login form!");
        return "/main/member/login";
    }

    // 로그인 처리 요청
    @PostMapping("/login")
    public String loginPro(String id, String pw, String auto, HttpServletResponse response, HttpSession session, Model model) {
        log.info("POST /login 로그인처리!!!");
        log.info("id: {}", id);
        log.info("pw: {}", pw);
        log.info("auto: {}", auto); // 체크했으면 auto, 체크안했으면 null

        return "/main/member/loginPro"; // 로그인 결과 페이지
    }
}
