package indi.icear.telegramframework.telegrambot;

import indi.icear.telegramframework.configuration.TelegramBotProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * TelegramBot事件的监听者，同时负责发布全局TelegramBot事件
 */
@Component
public class TelegramBotEventDispatcher {
    private Logger logger = LogManager.getLogger(TelegramBotEventDispatcher.class);

    private JmsMessagingTemplate jmsMessagingTemplate;

    public TelegramBotEventDispatcher(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    public void dispatchEvent(Update update) {
        /*
         * 设置事件接收器，然后根据事件选择对应的消息通道发送
         */

        //调用一个分类器，把Update的类型确定下来，然后对应发布
        switch (getEventType(update)) {
            case NORMAL_MESSAGE:
                jmsMessagingTemplate.convertAndSend(TelegramBotMessageDestination.normalMessageDestination, update);
                break;
            case ORDER_MESSAGE:
                Map<String, Object> headers = new HashMap<>();
                headers.put("order", getMessageOrder(update.getMessage()));
                jmsMessagingTemplate.convertAndSend(TelegramBotMessageDestination.orderMessageDestinationHeader, update, headers);
                break;
            case REPLY_MESSAGE:
                //TODO 增加header用于标记被回复的消息来源
                jmsMessagingTemplate.convertAndSend(TelegramBotMessageDestination.replyMessageDestination);
                break;
            case UNSUPPORTED_MESSAGE:
            default:
                logger.warn("unsupported message type, ignored");
                logger.debug(update);
        }
    }

    /**
     * 根据Update判断事件类型
     *
     * @param update Update事件
     * @return 事件类型
     */
    private EventType getEventType(@NotNull Update update) {
        Optional<Message> message = Optional.ofNullable(update.getMessage());
        if (message.isPresent()) {
            if (isOrderMessage(message.get())) return EventType.ORDER_MESSAGE;
            if (message.get().isReply()) return EventType.REPLY_MESSAGE;
            return EventType.NORMAL_MESSAGE;
        }
        return EventType.UNSUPPORTED_MESSAGE;
    }

    private boolean isOrderMessage(Message normalMessage) {
        return normalMessage.getText().startsWith("/");
    }

    private String getMessageOrder(Message normalMessage) {
        return normalMessage.getText().split(" ")[0];
    }


    /**
     * 事件类型
     */
    private enum EventType {
        /**
         * 命令消息
         */
        ORDER_MESSAGE("ORDER_MESSAGE"),

        /**
         * 普通消息
         */
        NORMAL_MESSAGE("NORMAL_MESSAGE"),

        /**
         * 回复消息
         */
        REPLY_MESSAGE("REPLY_MESSAGE"),

        /**
         * 不支持的消息
         */
        UNSUPPORTED_MESSAGE("UNSUPPORTED_MESSAGE");

        private String string;

        EventType(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }
}
