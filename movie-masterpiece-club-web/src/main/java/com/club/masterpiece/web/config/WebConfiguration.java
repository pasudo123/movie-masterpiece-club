package com.club.masterpiece.web.config;

import com.club.masterpiece.web.config.filter.RequestBodyXSSFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * reference
 * - https://ivvve.github.io/2018/12/18/java/Spring/lucy/
 * - https://serverwizard.tistory.com/67
 */
@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final ObjectMapper mapper;
    private static final int FIRST = 1;
    private static final String[] PATTERNS = {"/*"};

    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> formDataXSSFilterRegistrationBean(){
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(FIRST);
        registrationBean.addUrlPatterns(PATTERNS);
        return registrationBean;
    }

    /**
     * vue quill editor 자체적으로 XSS Filter 처리가 된다.
     * 스크립트 문이 자동으로 치환이 됨. 하단의 htmlCharacterEscapes(), configureMessageConverters() 가 없어도 무방하다.
     */
//    @Bean
//    public FilterRegistrationBean<XssEscapeServletFilter> requestBodyXSSFilterRegistrationBean() {
//        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new RequestBodyXSSFilter());
//        registrationBean.addUrlPatterns(PATTERNS);
//        return registrationBean;
//    }

    @Bean
    public HttpMessageConverter<?> htmlCharacterEscapes(){

        // ObjectMapper 에 문자처리 기능 삽입.
        mapper.getFactory().setCharacterEscapes(new AntiXSSConfiguration.HTMLCharacterEscapes());

        // MessageConverter 에 mapper 설정.
        return new MappingJackson2HttpMessageConverter(mapper);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.add(htmlCharacterEscapes());
    }
}
