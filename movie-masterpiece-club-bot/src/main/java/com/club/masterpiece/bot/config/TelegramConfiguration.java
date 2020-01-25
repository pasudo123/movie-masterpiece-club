package com.club.masterpiece.bot.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * - spring boot 2.2 부터는 @EnableConfigurationProperties 이 필요하지 않음
 * - @SpringBootApplication 에서 알아서 @ConfigurationProperties 를 읽어들임
 * - 추가적으로 @Configuration 을 등록할 필요도 없고 알아서 빈으로 등록됨.
 *
 * - 현재 버전은 [ spring boot 1.8.x ] 이다.
 *
 */
@Getter
@ConfigurationProperties("telegram")
public class TelegramConfiguration {

    private Bot bot;
    private Admin admin;

    @Getter
    public class Bot{

        private String name;
        private String username;
        private String token;
        private String chatId;

        public void setName(String name) {
            this.name = name;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
        }
    }

    @Getter
    public class Admin {

        private String name;
        private String username;
        private String chatId;

        public void setName(String name) {
            this.name = name;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
        }
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
