package indi.telegramframwork.context.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import indi.telegramframwork.context.telegrambot.event.TelegramBotEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 用于创建TelegramBot的上下文
 */
@Component
public class TelegramBotContext {
    @Value("#{setting['telegramBot.token']}")
    protected String telegramBotToken;
    private Logger logger = LogManager.getLogger(TelegramBotContext.class);

    @Bean
    public TelegramBot getTelegramBot() {
        logger.info("connecting to telegram bot service via token...");
        logger.trace("telegramBot.token = " + telegramBotToken);
        TelegramBot telegramBot = new TelegramBot(telegramBotToken);
        logger.debug("telegram bot service connect result: " + telegramBot.toString());
        return telegramBot;
    }

}
