package com.club.masterpiece.bot;

import com.club.masterpiece.bot.telegram.GulagbuBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {

        /** API Context 초기화 **/
        ApiContextInitializer.init();

        SpringApplication.run(BotApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(final GulagbuBot gulagbuBot) {

        return args -> {

            /** Bot 인스턴스 등록 **/
            TelegramBotsApi botsApi = new TelegramBotsApi();

            /** Bot 등록 **/
            botsApi.registerBot(gulagbuBot);
        };
    }
}
