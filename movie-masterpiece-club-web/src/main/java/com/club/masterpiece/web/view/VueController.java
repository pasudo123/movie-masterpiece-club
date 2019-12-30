package com.club.masterpiece.web.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pasudo123 on 2019-09-30
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Controller
@Slf4j
public class VueController {

    @Value("${redirect-url}")
    private String redirectUrl;

    @RequestMapping("/login")
    public String login() {
        log.info("로그인 페이지");
        return "login";
    }

    @RequestMapping("/masterpiece")
    public String masterpiece() {
        log.info("메인 페이지");
        return "masterpiece";
    }

    @RequestMapping("/login/success")
    public String loginSuccess() {
        log.info("로그인 성공");
        return "redirect:" + redirectUrl;
    }
}