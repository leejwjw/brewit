package com.pt.brewit.config;

import com.pt.brewit.filter.LogFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class FilterConfig {
    // 필터 등록
    @Bean
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();

        filterBean.setFilter(new LogFilter());
        filterBean.addUrlPatterns("/*"); // 필터 적용할 URL 패턴 지정
        filterBean.setOrder(1); // 필터 순서

        return filterBean;
    }
    /*
    @Bean
    public FilterRegistrationBean<Filter> OtherFilter() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();

        //filterBean.setFilter(new XxxFilter());
        //filterBean.addUrlPatterns("/*"); // 필터 적용할 URL 패턴 지정
        //filterBean.setOrder(2); // 필터 순서

        return filterBean;
    }
    */
}
