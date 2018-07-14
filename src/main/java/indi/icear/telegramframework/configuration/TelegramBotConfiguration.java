package indi.icear.telegramframework.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;
import org.telegram.telegrambots.generics.WebhookBot;

import java.util.List;

@Configuration
public class TelegramBotConfiguration implements CommandLineRunner {
    private final List<LongPollingBot> longPollingBots;
    private Logger logger = LogManager.getLogger(TelegramBotConfiguration.class);
    @Autowired
    private TelegramBotsApi telegramBotsApi;

    public TelegramBotConfiguration(List<LongPollingBot> longPollingBots) {
        this.longPollingBots = longPollingBots;
    }

    @Override
    public void run(String... args) {
        try {
            for (LongPollingBot bot : longPollingBots) {
                telegramBotsApi.registerBot(bot);
            }
            logger.info("CoolQ service connected");

//            for (WebhookBot bot : webHookBots) {
//                telegramBotsApi.registerBot(bot);
//            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Bean
    @ConditionalOnMissingBean(TelegramBotsApi.class)
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }
}
