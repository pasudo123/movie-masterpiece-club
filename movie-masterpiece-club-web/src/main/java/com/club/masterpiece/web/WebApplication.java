package com.club.masterpiece.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.club.masterpiece.web", "com.club.masterpiece.storage"})
@SpringBootApplication
public class WebApplication {

    private static final String PROPERTIES =
            "spring.config.additional-location="
                    + "/data/etc/gulagbu.com/application-prod.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebApplication.class)
                .properties(PROPERTIES).run(args);
    }
}
