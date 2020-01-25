package com.club.masterpiece.bot;

import com.club.masterpiece.bot.telegram.GulagbuBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@SpringBootApplication
public class BotApplication {

    private static final String PROPERTIES =
            "spring.config.additional-location="
                    + "/data/etc/gulagbu.com/application-override.yml";

    public static void main(String[] args) {

        /** API Context 초기화 **/
        ApiContextInitializer.init();

        new SpringApplicationBuilder(BotApplication.class)
                .properties(PROPERTIES).run(args);
    }

    @Bean
    public CommandLineRunner runner(final GulagbuBot gulagbuBot) {

        return args -> {

            // 봇 인스턴스 등록
            TelegramBotsApi botsApi = new TelegramBotsApi();

            // 봇 등록
            botsApi.registerBot(gulagbuBot);
        };
    }
}
