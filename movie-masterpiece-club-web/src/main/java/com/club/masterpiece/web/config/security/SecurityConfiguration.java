package com.club.masterpiece.web.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

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

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl(defaultSuccessUrl);

        http.oauth2Login()
                .loginPage("/login")
                .redirectionEndpoint()
                .baseUri("/login/oauth2/callback/**")
                    .and()
                .userInfoEndpoint()
                .userService(securityOAuth2UserService)
                    .and()
                .defaultSuccessUrl(defaultSuccessUrl);

        http.logout()
                .logoutSuccessUrl(logoutSuccessUrl)
                .clearAuthentication(true);
    }

    @Bean
    @Profile("dev")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
