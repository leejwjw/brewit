package com.pt.brewit.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Slf4j
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
                                       , AuthenticationException exception) throws IOException, ServletException {

        log.info("Login Failed!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // request 객체
        //String username = request.getParameter("username"); // 파라미터 조회
        //String password = request.getParameter("password");

         //exception 객체
        //String exceptionMessage = exception.getMessage();
        String cause = "로그인 실패";
        if(exception instanceof UsernameNotFoundException) {
            cause = "no-username";  // 계정 없음
        }else if(exception instanceof BadCredentialsException) {
            cause = "bad-credentials"; // 비밀번호 불일치
        }else if(exception instanceof CredentialsExpiredException) {
            cause = "credentials-expired"; // 비밀번호 만료
        }else if(exception instanceof AccountExpiredException) {
            cause = "account-expired"; // 계정 만료
        }else if(exception instanceof DisabledException) {
            cause = "disabled"; // 계정 비활성화
        }else if(exception instanceof LockedException) {
            cause = "locked"; // 계정 잠심
        }else {
            cause = "unknown"; // 알수없는 에러...
        }
        // response 객체
        response.sendRedirect("/login?fail=" + cause);
    }
}
