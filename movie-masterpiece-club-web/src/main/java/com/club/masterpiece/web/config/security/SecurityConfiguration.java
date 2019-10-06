//package com.club.masterpiece.web.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Created by pasudo123 on 2019-09-29
// * Blog: https://pasudo123.tistory.com/
// * Email: oraedoa@gmail.com
// **/
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//    @Value("${spring.profiles.active}")
//    private String active;
//
//    private static final String PROD = "prod";
//    private static final String DEV = "dev";
//
//    private final SecurityOAuth2UserService securityOAuth2UserService;
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {}
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        /** h2 console 이용을 위함 **/
//        http.csrf()
//                .disable()
//                .headers()
//                .frameOptions().disable();
//
//        http.authorizeRequests()
//                .antMatchers("/error", "/favicon.ico", "/**/*.jpg", "/**/*.png", "/**/*.css", "/**/*.js", "/**/*.map")
//                .permitAll()
//                .antMatchers("/login/**", "/gulagbu-api/**", "/masterpiece/**   ")
//                .permitAll();
//
//        http.authorizeRequests()
//                .anyRequest().authenticated();
//
//        http.oauth2Login()
//                .loginPage("/login")
//                .redirectionEndpoint()
//                .baseUri("/login/oauth2/callback/**")
//                .and()
//                .userInfoEndpoint()
//                .userService(securityOAuth2UserService)
//                .and()
//                .defaultSuccessUrl("/login-success");
//    }
//}
