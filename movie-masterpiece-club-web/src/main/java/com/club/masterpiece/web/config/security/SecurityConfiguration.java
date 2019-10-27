package com.club.masterpiece.web.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.ServletContext;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // reference : https://www.baeldung.com/spring-security-method-security
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityOAuth2UserService securityOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /** h2 console 이용을 위함 **/
        http.csrf()
                .disable()
                .headers()
                .frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/error", "/favicon.ico", "/**/*.jpg", "/**/*.png", "/**/*.css", "/**/*.js", "/**/*.map")
                .permitAll()
                .antMatchers("/login/**")
                .permitAll();

        http.authorizeRequests()
                .anyRequest().authenticated();

        http.oauth2Login()
                .loginPage("/login")
                .redirectionEndpoint()
                .baseUri("/login/oauth2/callback/**")
                .and()
                .userInfoEndpoint()
                .userService(securityOAuth2UserService)
                .and()
                .defaultSuccessUrl("/login/success");

        http.logout()
                .logoutSuccessUrl("/login")
                .clearAuthentication(true);
    }
}
