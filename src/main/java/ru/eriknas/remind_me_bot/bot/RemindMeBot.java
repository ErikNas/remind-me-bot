package ru.eriknas.remind_me_bot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class RemindMeBot implements LongPollingSingleThreadUpdateConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RemindMeBot.class);

    private static final String START = "/start";
    private static final String HELP = "/help";

    private TelegramClient telegramClient = new OkHttpTelegramClient("12345:YOUR_TOKEN");


    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println(update.getMessage().getText());
        }

//        var setWebhook = new SetWebhook;
//        telegramClient.execute()
    }

//    public RemindMeBot(@Value("${bot.token}") String botToken) throws TelegramApiException {
////        super(botToken);
//
//        var botsApplication = new TelegramBotsLongPollingApplication();
//        botsApplication.registerBot(botToken, this);
//    }

//    @Override
//    public String getBotUsername() {
//        return "jaga_remind_me_bot";
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (!update.hasMessage() || !update.getMessage().hasText()) {
//            return;
//        }
//        String message = update.getMessage().getText();
//        Long chatId = update.getMessage().getChatId();
//        switch (message) {
//            case START -> {
//                String userName = update.getMessage().getChat().getUserName();
//                startCommand(chatId, userName);
//            }
//            case HELP -> helpCommand(chatId);
//            default -> unknownCommand(chatId);
//        }
//    }
//
//    private void startCommand(Long chatId, String userName) {
//        var text = """
//                Добро пожаловать в бот, %s!
//
//                Бот помогает напомнить про сообщение, которые остались в чате без ответа
//                от пользователя, которому они были адресованы.
//
//                Дополнительные команды:
//                /help - получение справки
//                """;
//        var formattedText = String.format(text, userName);
//        sendMessage(chatId, formattedText);
//    }
//    private void helpCommand(Long chatId) {
//        var text = """
//                Справочная информация по боту
//
//                В процессе наполнения...
//                """;
//        sendMessage(chatId, text);
//    }
//
//    private void unknownCommand(Long chatId) {
//        var text = "Не удалось распознать команду!";
//        sendMessage(chatId, text);
//    }
//
//    private void sendMessage(Long chatId, String text) {
//        var chatIdStr = String.valueOf(chatId);
//        var sendMessage = new SendMessage(chatIdStr, text);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            LOG.error("Ошибка отправки сообщения", e);
//        }
//    }
}
