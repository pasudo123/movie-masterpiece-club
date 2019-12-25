//package com.club.masterpiece.web.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * Created by pasudo123 on 2019-12-24
// * Email: oraedoa@gmail.com
// **/
//@Configuration
//public class GulagbuWebMvcConfiguration implements WebMvcConfigurer {
//
//    /** @EnableWebMvc 가 있고 없고에 따라 차이가 있음. 아티클 조회가 안되고 됨. **/
//
//    /**
//     * addResourceHandler : 지정된 URL 경로를 기반으로 정적 리소스를 serve 한다.
//     * addResourceLocations : 정적 리소스가 위치한 유효한 디렉토리
//     */
//    private static final String RESOURCE_HANDLERS = "/**";
//    private static final String RESOURCE_LOCATIONS = "classpath:/statics/";
//
//    private static final String LOCAL_HANDLERS = "/file/**";
//    private static final String LOCAL_LOCATIONS = "file:///D:/gulagbu-static/images/";
//
//    private static final String VPS_HANDLERS = "/data/gulagbu-static/**";
//    private static final String VPS_LOCATIONS = "/data/gulagbu-static/images/";
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        registry.addResourceHandler(RESOURCE_HANDLERS)
//                .addResourceLocations(RESOURCE_LOCATIONS);
//
//        registry.addResourceHandler(LOCAL_HANDLERS)
//                .addResourceLocations(LOCAL_LOCATIONS);
//
//        registry.addResourceHandler(VPS_HANDLERS)
//                .addResourceLocations(VPS_LOCATIONS);
//    }
//}
