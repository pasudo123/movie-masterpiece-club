package com.club.masterpiece.web.config.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * HttpServletRequestWrapper
 *  - 서블릿 요청 및 응답 래퍼 클래스. 해당 클래스를 상속받아 사용.
 *
 * Created by pasudo123 on 2020-01-05
 * Email: oraedoa@gmail.com
 **/
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public final String redirectUrl;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CustomHttpServletRequestWrapper(final HttpServletRequest request, final String ParamUrl) {
        super(request);
        this.redirectUrl = ParamUrl + request.getRequestURI();
    }

    @Override
    public StringBuffer getRequestURL() {
        return new StringBuffer(redirectUrl);
    }
}
