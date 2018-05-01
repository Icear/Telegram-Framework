package indi.telegramframwork.plugin.logplugin;

import indi.telegramframwork.context.telegrambot.event.TelegramBotEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LogPlugin implements ApplicationListener<TelegramBotEvent> {
    private Logger logger = LogManager.getLogger(LogPlugin.class);

    public LogPlugin() {
        logger.debug("log plugin activate");
    }

    @EventListener
    @Override
    public void onApplicationEvent(TelegramBotEvent telegramBotEvent) {
        logger.debug("event start, receive telegram bot event");
        logger.trace("receive event detail: " + telegramBotEvent.toString());
    }

}
