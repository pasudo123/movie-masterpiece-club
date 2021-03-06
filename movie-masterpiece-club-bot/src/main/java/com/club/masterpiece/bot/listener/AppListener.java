package com.club.masterpiece.bot.listener;

import com.club.masterpiece.bot.telegram.GulagbuBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppListener {

    private static final String FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private final GulagbuBot gulagbuBot;

    @EventListener
    public void startUpApplicationServer(final ContextRefreshedEvent event) {

        String messageBuilder = "START Gulagbu Bot\n" + getNowTime();
        gulagbuBot.sendMessageToAdmin(messageBuilder);
    }

    @EventListener
    public void shutDownApplicationServer(final ContextClosedEvent event) {

        /**
         * 특정 프로필에 대해서만 처리를 할 것인지 여부 결정.
         */
        String messageBuilder = "STOP Gulagbu Bot\n" + getNowTime();
        gulagbuBot.sendMessageToAdmin(messageBuilder);
        gulagbuBot.onClosing();
    }

    private String getNowTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMATTER));
    }
}
