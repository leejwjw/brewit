package com.pt.brewit.config;

import com.pt.brewit.security.CustomAccessDeniedHandler;
import com.pt.brewit.security.CustomLoginFailureHandler;
import com.pt.brewit.security.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링 환결설정과 관련된 스프링빈으로 등록 어노테이션
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 어노테이션
// 내부적으로 SpringSecurityFilterChain이 동작하여, URL 필터가 적용된다.
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService; // remember-me 기능 사용시 필요

    @Bean  // 메서드가 리턴하는 객체를 스프링빈으로 등록해줘~
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers(new AntPathRequestMatcher("/mypage/**")).hasAnyRole("member", "admin", "seller")
                    .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole("admin","seller")
                    .anyRequest().permitAll())
            .csrf((csrf) -> csrf.disable()) // crsf 기능 끄기
            .formLogin((formLogin) -> formLogin
                    .loginPage("/login")
                    .usernameParameter("email")
                    //.passwordParameter("password")
                    .successHandler(new CustomLoginSuccessHandler())
                    .failureHandler(new CustomLoginFailureHandler()))
//                    .defaultSuccessUrl("/", true))
            .logout(logout -> logout
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true))
            .rememberMe(rememberMeConfig -> rememberMeConfig
                    //.rememberMeParameter("remember-me") // 로그인시 자동로그인 기능으로 넘오는 파라미터명 (checkbox의 name속성값)
                    .tokenValiditySeconds(3600) // 초단위로 유효기간 설정 default는 14일 
                    //.alwaysRemember(false) // 체크안해도 항상 자동로그인 설정할지 여부 default false
                    .userDetailsService(userDetailsService));
            //.exceptionHandling(handler -> handler.accessDeniedHandler(accessDeniedHandler()));

        return http.build();
    }

    // 시큐리티 인증체크 리소스들 제외 : css, js, img
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers("/admin_static/**", "/img/**", "/main/**");
    }

    @Bean // 비밀번호 암호화시 사용할 클래스 스프링빈으로 등록
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 구현 클래스
    }

    @Bean // 시큐리티 인증 담당, 구현한 UserDetailsService 클래스 사용
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 접근 거부 핸들러
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
