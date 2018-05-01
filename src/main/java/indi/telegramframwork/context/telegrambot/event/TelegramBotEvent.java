package indi.telegramframwork.context.telegrambot.event;

import com.pengrad.telegrambot.model.Update;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEvent;

/**
 * TelegramBot相关事件定义
 * 用于储存Telegram事件同时继承ApplicationEvent以进入Spring事件模型
 */
public class TelegramBotEvent extends ApplicationEvent {
    private Logger logger = LogManager.getLogger(TelegramBotEvent.class);

    public TelegramBotEvent(Update update) {
        super(update);
        logger.debug("telegram bot event created");
        logger.trace("created with Update: " + update.toString());
    }

    @Override
    public Update getSource() {
        Update source = (Update) super.getSource();
        logger.trace("provide source: " + source.toString());
        return source;
    }

    @Override
    public String toString() {
        return "TelegramBotEvent{" +
                "source=" + source.toString() +
                '}';
    }
}
