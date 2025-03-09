package ru.eriknas.remind_me_bot.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eriknas.remind_me_bot.bot.MyClient;

@Service
public class Commands {

    @Autowired
    MyClient client;

    public void startCommand(Long chatId, String userName) {
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

    public void helpCommand(Long chatId) {
        var text = """
                Справочная информация по боту

                В процессе наполнения...
                """;
        sendMessage(chatId, text);
    }

    public void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        client.sendMessage(chatIdStr, text);
    }
}
