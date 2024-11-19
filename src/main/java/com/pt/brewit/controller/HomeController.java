package com.pt.brewit.controller;


import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.mapper.MemberMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j // Lombok에서 제공해주는 어노테이션 - 로그 사용하기 위해 클래스 위에 선언
@RequiredArgsConstructor
public class HomeController {

    private final MemberMapper memberMapper;

    @RequestMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        log.info("home");
        // 쿠키 있는지 검사
        Cookie[] cookies = request.getCookies();
        String cemail = null, cpw = null, cauto = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) { // 쿠키 반복해 돌려서
                // 각각 위 맞는 변수에 값 꺼내 체우기
                if (cookie.getName().equals("cemail")) { cemail = cookie.getValue(); }
                if (cookie.getName().equals("cpw")) { cpw = cookie.getValue(); }
                if (cookie.getName().equals("cauto")) { cauto = cookie.getValue(); }

                log.info("1------------------cemail:"+cemail);
                log.info("2------------------cpw:"+cpw);
                log.info("3------------------cauto:"+cauto);
            }
        }

        if(cemail != null && cpw != null && cauto != null) {
            // 로그인 처리 : session에 sid 추가, 쿠키 갱신
            MemberDTO memberDTO = memberMapper.emailPwCheck(cemail, cpw);
            if(memberDTO != null) {
                session.setAttribute("semail", cemail);
                // 쿠키 갱신(같은 쿠키 만들어 전송 )
                createCookie(cemail, cpw, cauto, response);
            }
        }

        return "main/mainPage";
    }

    private void createCookie(String email, String pw, String auto, HttpServletResponse response) {

        log.info("------------------createCookie");

        Cookie c1 = new Cookie("cemail", email);  // 쿠키 객체 생성
        Cookie c2 = new Cookie("cpw", pw);
        Cookie c3 = new Cookie("cauto", auto);
        c1.setMaxAge(60 * 60); // 유효기간 설정 (예제는 1시간)
        c2.setMaxAge(60 * 60);
        c3.setMaxAge(60 * 60);
        response.addCookie(c1); // 응답객체에 쿠키 추가 -> 브라우저에 전송
        response.addCookie(c2);
        response.addCookie(c3);
    }

    // 로그인 폼 요청
    @GetMapping("/login")
    public String login() {
        log.info("login form!");
        return "/main/login";
    }

    @PostMapping("/login")
    public String loginPro(String email, String pw, String auto, HttpServletResponse response, HttpSession session, Model model) {
        log.info("POST /login 로그인처리!!!");
        log.info("email: {}", email);
        log.info("pw: {}", pw);
        log.info("auto: {}", auto); // 체크했으면 auto, 체크안했으면 null
        // 로그인 처리
        // DB에 사용자가 입력한 id와 pw가 일치하는 데이터가 있는지 확인
        boolean result = false;

        MemberDTO memberDTO = memberMapper.emailPwCheck(email, pw);

        if(memberDTO != null) {
            // 일치한다 -> 로그인 처리 -> 로그인 잘됐다는 결과 화면에 주기
            result = true; // 로그인결과 true로 지정
            log.info("memberDTO: {}", memberDTO);
            session.setAttribute("semail", memberDTO.getEmail()); // 사용자 id값 저장
            if(auto != null) { // 자동로그인 체크 했다면,
                createCookie(email, pw, auto, response);
            }
        }
        // 일치X -> 일치하지 않다는 결과 화면에 주기
        model.addAttribute("result", result); // 화면에 id,pw 검사결과 전달
        return "/main/loginPro"; // 로그인 결과 페이지
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.invalidate(); // 세션 속성 모두 삭제
        // 쿠키 삭제
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("cemail") || cookie.getName().equals("cpw") || cookie.getName().equals("cauto")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        return "redirect:/";  // 홈으로 강제 이동
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    //tea 상품페이지
    //Test
    //Test22
    @GetMapping("/details")
    public String details(Model model) {
        model.addAttribute("name", "상품명1");
        model.addAttribute("sale_price", "123,456");

        return "main/productExample";
    }
}
