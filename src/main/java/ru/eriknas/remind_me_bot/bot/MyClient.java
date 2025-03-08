package ru.eriknas.remind_me_bot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class MyClient {

    @Autowired
    TelegramClient client;

    public void sendMessage(String chatId, String message) {
        try {
            client.execute(new SendMessage(chatId, message));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
