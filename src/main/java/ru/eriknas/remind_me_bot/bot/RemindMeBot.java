package ru.eriknas.remind_me_bot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.eriknas.remind_me_bot.logic.Commands;

import java.util.concurrent.TimeUnit;

@Component
public class RemindMeBot implements LongPollingSingleThreadUpdateConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RemindMeBot.class);

    private static final String START = "/start";
    private static final String HELP = "/help";

    @Autowired
    Commands commands;

    @Override
    public void consume(Update update) {

        // Если тэгнули человека, то записать в историю с напоминанием
        // Если это ответ от тэгнутого - снять напоминание
        // Если это реакция от тэгнутого - снять напоминание
        // Если это сообщение от тэгнутого сразу после тэгирования - снять напоминание

        // Записать update в историю

        // Сохранить в историю
        // Проверить, если это команда, то выполнить
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println(update.getMessage().getText());
        }
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        switch (message) {
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                commands.startCommand(chatId, userName);
            }
            case HELP -> commands.helpCommand(chatId);
            default -> commands.unknownCommand(chatId);
        }
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void remind() {
        // Удалить все старые напоминания из чата

        // Добавить новые напоминания
        System.out.println("Work!");
    }
}
