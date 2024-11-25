package com.pt.brewit.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init!!!");
    }

    @Override
    public void destroy() {
        log.info("log filter destroy!!!");
    }

    // 메인 느낌의 메서드
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 우리가 아는 request, response 객체 타입으로 형변환해서 사용
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("doFilter - requestURI : {}", request.getRequestURI());
        // 필수 작성!! 다음 필터가 동작하기 위해 호출
        filterChain.doFilter(request, response); // 다음 필터가 있으면 필터 호출, 없으면 서블릿 호출
    }

}
