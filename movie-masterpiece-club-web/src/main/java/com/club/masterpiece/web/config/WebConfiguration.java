package com.club.masterpiece.web.config;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private static final int FIRST = 1;
    private static final String PATTERN = "*.ftl";

    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean(){
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(FIRST);
        registrationBean.addUrlPatterns(PATTERN);

        return registrationBean;
    }
}
