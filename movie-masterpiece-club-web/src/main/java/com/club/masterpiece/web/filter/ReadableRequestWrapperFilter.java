//package com.club.masterpiece.web.filter;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.LogRecord;
//
///**
// * Created by pasudo123 on 2019-10-28
// * Blog: https://pasudo123.tistory.com/
// * Email: oraedoa@gmail.com
// *
// * reference : https://github.com/taetaetae/request_logging/blob/master/src/main/java/com/taetaetae/request_logging/filter/ReadableRequestWrapperFilter.java
// *
// **/
//@Slf4j
//@WebFilter(urlPatterns = "/*")
//
//public class ReadableRequestWrapperFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        /** 어떤 것도 수행하지 않는다. **/
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//    }
//
//    @Override
//    public void destroy() {
//        /** 어떤 것도 수행하지 않는다. **/
//    }
//
//    public class ReadableRequestWrapper extends HttpServletRequestWrapper{
//
//        private final Charset enconding;
//        private byte[] rawData;
//        private Map<String, String[]> params = new HashMap<>();
//
//        public ReadableRequestWrapper(HttpServletRequest request) {
//            super(request);
//        }
//    }
//}
