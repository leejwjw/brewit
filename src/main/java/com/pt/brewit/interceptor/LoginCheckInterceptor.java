package com.pt.brewit.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("interceptor preHandle requestURI: {}", requestURI);
        /*
        request.getSession().setAttribute("data", "hello"); // 세션에 속성 추가
        HttpSession session = request.getSession();
        String sid = (String)session.getAttribute("sid");
        Object springSecurityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");// (세션 속성 모두 삭제) 세션에 저장된 Authentication 정보도 삭제
        if(springSecurityContext == null) {
            log.info("로그인 안함");
            response.sendRedirect("/login");
            return false; // 다음 필터 취소!
        }*/

        return true; // 다음 인터셉터 이어서 실행~
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("interceptor postHandle!!!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("interceptor afterCompletion!!!");
    }
}
