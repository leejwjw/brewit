package com.pt.brewit.config;

import com.pt.brewit.interceptor.LoginCheckInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 인터셉터 적용시 WebMvcConfigurer 구현하는 설정 클래스 작성
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**") // 적용할 경로 패턴
                .excludePathPatterns("/", "/signup", "/login"); // 제외시킬 경로들

        // 추가할 인터셉터 이곳에 추가
        //registry.addInterceptor(new XxxInterceptor())
        //       .order(2)
        //       ...

    }
}
