package com.club.masterpiece.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

//    @Bean
//    public ConfigurableServletWebServerFactory webServerFactory(final GracefulShutdownConfiguration shutdownConfiguration) {
//
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addConnectorCustomizers(shutdownConfiguration);
//
//        return factory;
//    }
}
