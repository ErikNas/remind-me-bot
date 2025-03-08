package ru.eriknas.remind_me_bot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.DefaultGetUpdatesGenerator;
import org.telegram.telegrambots.meta.TelegramUrl;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.eriknas.remind_me_bot.bot.RemindMeBot;

import java.util.List;

@Configuration
public class RemindMeBotConfiguration {

    @Bean
    public TelegramBotsLongPollingApplication telegramBotsApi(
            @Value("${bot.token}") String botToken,
            RemindMeBot remindMeBot) throws TelegramApiException {

        // Create and configure the TelegramBotsLongPollingApplication instance
        TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
        DefaultGetUpdatesGenerator getUpdatesGenerator = new DefaultGetUpdatesGenerator(List.of("message",
                "edited_channel_post", "callback_query", "message_reaction"));
        botsApplication.registerBot(botToken,
                () -> TelegramUrl.DEFAULT_URL,
                getUpdatesGenerator,
                remindMeBot);

        return botsApplication;
    }
}
