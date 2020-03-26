package com.club.masterpiece.web.config.filter;


import com.club.masterpiece.web.config.filter.CustomRequestWrapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Slf4j
public class RequestBodyXSSFilter extends XssEscapeServletFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        CustomRequestWrapper requestWrapper = null;

        try{
            requestWrapper = new CustomRequestWrapper(request);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RequestBody XSSFilter failed : {}", e.getMessage());
        }

        chain.doFilter(requestWrapper,  response);
    }
}
