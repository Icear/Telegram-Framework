package indi.telegramframework.plugin.logplugin.filter;

import indi.telegramframework.context.telegrambot.TelegramBotEventFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 日志用过滤器
 * 用于日志记录每一个Event事件的内容，内容仅输出于Trace Level
 */
@Component
public class LogFilter {
    private Logger logger = LogManager.getLogger(LogFilter.class);

    public LogFilter() {
        logger.debug("log filter activate");
    }

    @Bean
    public TelegramBotEventFilter getTelegramBotEventFilter() {
        return telegramBotEvent -> {
            logger.debug("filter start, receive telegram bot event");
            logger.trace("receive event detail: " + telegramBotEvent.toString());
            return true;
        };
    }
}
