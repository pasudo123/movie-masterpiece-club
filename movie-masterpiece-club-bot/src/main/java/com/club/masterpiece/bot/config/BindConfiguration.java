package com.club.masterpiece.bot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TelegramConfiguration.class)
public class BindConfiguration {

    /**
     * - @EnableConfigurationProperties({ class-name }) 작성.
     * - class-name 은 여러개가 가능하다.
     */
}
