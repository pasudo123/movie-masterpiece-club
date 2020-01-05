package com.club.masterpiece.web.config.security;

import com.club.masterpiece.web.config.filter.OAuth2Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // reference : https://www.baeldung.com/spring-security-method-security
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${domain}")
    private String domain;

    @Value("${security-oauth2-redirect-uri}")
    private String oauth2BaseUrl;

    @Value("${default-success-url}")
    private String defaultSuccessUrl;

    @Value("${logout-success-url}")
    private String logoutSuccessUrl;

    private final SecurityOAuth2UserService securityOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /** h2 console 이용을 위함 **/
        http.csrf()
                .disable()
                .headers()
                .frameOptions().disable();

        /** 일반 필터 타기 이전에 커스텀 필터 등록 : https 에서 google oauth2 문제. **/
        http.addFilterBefore(new OAuth2Filter(domain), OAuth2LoginAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers(
                        "/error",
                        "/favicon.ico",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.map")
                    .permitAll()
                .antMatchers(HttpMethod.GET,"/actuator/health")
                    .permitAll()
                .antMatchers("/login/**")
                    .permitAll()
                .and()
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .httpBasic();

//        http.formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl(defaultSuccessUrl);

        http.oauth2Login()
                .loginPage("/login")
                .redirectionEndpoint().baseUri(oauth2BaseUrl)
                    .and()
                .userInfoEndpoint()
                .userService(securityOAuth2UserService)
                    .and()
                .defaultSuccessUrl(defaultSuccessUrl);

        http.logout()
                .logoutSuccessUrl(logoutSuccessUrl)
                .clearAuthentication(true);
    }

//    @Bean
//    @Profile("dev")
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
