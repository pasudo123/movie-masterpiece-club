package com.club.masterpiece.web.config;

import com.club.masterpiece.web.config.filter.RequestBodyXSSFilter;
import com.club.masterpiece.web.config.xss.HtmlCharacterEscapes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> requestBodyXSSFilterRegistrationBean() {
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestBodyXSSFilter());
        registrationBean.addUrlPatterns(PATTERNS);
        return registrationBean;
    }

    @Bean
    public HttpMessageConverter<?> htmlCharacterEscapes(){

        // ObjectMapper 에 문자처리 기능 삽입.
        mapper.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());

        // MessageConverter 에 mapper 설정.
        MappingJackson2HttpMessageConverter htmlEscapingConverter = new MappingJackson2HttpMessageConverter();
        htmlEscapingConverter.setObjectMapper(mapper);
        return htmlEscapingConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(htmlCharacterEscapes());
    }
}
