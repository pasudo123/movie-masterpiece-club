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

    @Value("${app.home-url}")
    private String homeUrl;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String masterpiece() {
        return "index";
    }

    @RequestMapping("/login/success")
    public String loginSuccess() {
        return "redirect:" + homeUrl;
    }
}