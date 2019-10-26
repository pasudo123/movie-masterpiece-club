package com.club.masterpiece.web.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pasudo123 on 2019-09-30
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Controller
public class VueController {

    @Value("${redirect-url}")
    private String redirectUrl;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/masterpiece")
    public String masterpiece() {
        return "masterpiece";
    }

    @RequestMapping("/login/success")
    public String loginSuccess() {
        return "redirect:" + redirectUrl;
    }
}
