package com.pt.brewit.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

// 접근 불가한 소스 요청 시, 핸들링해주는 클래스
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    // 오버라이딩 필수
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 접근 거부시 처리할 내용 작성
        String deniedUrl = "/accessDenied?exception=" + accessDeniedException.getMessage();
        response.sendRedirect(deniedUrl); // 해당 url 경로로 강제 이동
    }

}
