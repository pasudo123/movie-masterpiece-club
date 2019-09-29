package com.club.masterpiece.web.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .headers()
                .frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/error", "/favicon.ico", "/**/*.jpg", "/**/*.png", "/**/*.css", "/**/*.js", "/**/*.map")
                .permitAll()
                .antMatchers("/", "login/**")
                .permitAll();

        http.authorizeRequests()
                .anyRequest().authenticated();

        http.oauth2Login()
                .loginPage("/login")
                .redirectionEndpoint()
                    .baseUri("/login/oauth2/callback/**")
                    .and()
                .userInfoEndpoint()
                    .userService(null)
                    .and()
                .defaultSuccessUrl("/login-success");
    }
}
