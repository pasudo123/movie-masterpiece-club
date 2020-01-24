package com.club.masterpiece.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebApplication {

    private static final String PROPERTIES =
            "spring.config.additional-location="
                    + "/data/etc/gulagbu.com/application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebApplication.class)
                .properties(PROPERTIES).run(args);
    }
}
