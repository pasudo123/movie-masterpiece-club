package com.club.masterpiece.web.config.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by pasudo123 on 2020-01-05
 * Email: oraedoa@gmail.com
 **/
@Slf4j
public class OAuth2Filter implements Filter {

    private static final String CONTOUR = "===============================================================================";
    private final String url;

    public OAuth2Filter(final String url) {
        this.url = url;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        if(!log.isDebugEnabled()){
            return;
        }

        log.debug(CONTOUR);
        log.debug("OAuth2Filter init Method");
        log.debug("FilterConfig servletContext : {}", filterConfig.getServletContext());
        log.debug(CONTOUR);
    }

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(log.isDebugEnabled()) {
            log.debug(CONTOUR);
            log.debug("[before] request : {}", ((HttpServletRequest) request).getRequestURL().toString());
        }

        /** 커스텀한 ServletRequestWrapper 를 태우고, 이후에 필터 적용. **/
        request = new CustomRequestWrapper((HttpServletRequest) request, url);

        if(log.isDebugEnabled()) {
            log.debug("[after] changed request : {}", ((HttpServletRequest) request).getRequestURL().toString());
            log.debug(CONTOUR);
        }

        chain.doFilter(request, response);
    }
}
