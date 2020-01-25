package com.club.masterpiece.bot.telegram;

import com.club.masterpiece.bot.config.TelegramConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class GulagbuBot extends TelegramLongPollingBot {

    private final TelegramConfiguration telegramConfiguration;

    @Value("${telegram.bot.username")
    private String username;

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.admin.chatId}")
    private String adminChatId;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    /**
     * 메시지를 받고 이후 처리하는 비즈니스 로직.
     */
    @Override
    public void onUpdateReceived(Update update) {

    }

    public void sendMessageToGuest(final String message) {

        SendMessage sendMessage = new SendMessage()
                .setChatId("hellos")
                .setText(message);

        send(sendMessage);
    }

    public void sendMessageToAdmin(final String message) {

        SendMessage sendMessage = new SendMessage()
                .setChatId(adminChatId)
                .setText(message);

        send(sendMessage);
    }

    private void send(final SendMessage sendMessage) {

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("메시지 전송 시 에러가 발생하였습니다. : {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
