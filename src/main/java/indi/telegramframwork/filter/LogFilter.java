package indi.telegramframwork.filter;

import indi.telegramframwork.context.telegrambot.TelegramBotEventFilter;
import indi.telegramframwork.context.telegrambot.event.TelegramBotEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 日志用过滤器
 * 用于日志记录每一个Event事件的内容，内容仅输出于Trace Level
 */
@Component
public class LogFilter implements TelegramBotEventFilter {
    private Logger logger = LogManager.getLogger(LogFilter.class);

    @Override
    public boolean filterEvent(TelegramBotEvent telegramBotEvent) {
        logger.debug("filter start, receive telegram bot event");
        logger.trace("receive event detail: " + telegramBotEvent.toString());
        return true;
    }
}
