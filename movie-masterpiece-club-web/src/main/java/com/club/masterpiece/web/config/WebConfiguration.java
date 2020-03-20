package com.club.masterpiece.web.config;

import com.club.masterpiece.web.config.filter.RequestBodyXSSFilter;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * reference
 * - https://ivvve.github.io/2018/12/18/java/Spring/lucy/
 * - https://serverwizard.tistory.com/67
 */

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private static final int FIRST = 1;
    private static final String[] PATTERNS = {"/article"};

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
}
