package ru.eriknas.remind_me_bot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class RemindMeBot implements LongPollingSingleThreadUpdateConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RemindMeBot.class);

    private static final String START = "/start";
    private static final String HELP = "/help";

    @Autowired
    MyClient client;

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println(update.getMessage().getText());
        }
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        switch (message) {
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
            }
            case HELP -> helpCommand(chatId);
            default -> unknownCommand(chatId);
        }

    }

    private void startCommand(Long chatId, String userName) {
        var text = """
                Добро пожаловать в бот, %s!

                Бот помогает напомнить про сообщение, которые остались в чате без ответа
                от пользователя, которому они были адресованы.

                Дополнительные команды:
                /help - получение справки
                """;
        var formattedText = String.format(text, userName);
        sendMessage(chatId, formattedText);
    }

    private void helpCommand(Long chatId) {
        var text = """
                Справочная информация по боту

                В процессе наполнения...
                """;
        sendMessage(chatId, text);
    }

    private void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        client.sendMessage(chatIdStr, text);
    }
}
