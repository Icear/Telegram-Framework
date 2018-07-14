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

import java.util.Optional;


/**
 * TelegramBot事件的监听者，同时负责发布全局TelegramBot事件
 */
@Component
@EnableConfigurationProperties(TelegramBotProperties.class)
public class TelegramBotEventDispatcher {
    private Logger logger = LogManager.getLogger(TelegramBotEventDispatcher.class);

    private TelegramBotProperties properties;
    private JmsMessagingTemplate jmsMessagingTemplate;

    public TelegramBotEventDispatcher(TelegramBotProperties properties, JmsMessagingTemplate jmsMessagingTemplate) {
        this.properties = properties;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    public void dispatchEvent(Update update) {
        /*
         * 设置事件接收器，然后对应三种事件进行对应的消息发送
         */

        //调用一个分类器，把Update的类型确定下来，然后对应发布
        //直接发布吧。。。三种消息都是同一个。。。


    }

    /**
     * 根据Update判断事件类型
     *
     * @param update Update事件
     * @return 事件类型
     */
    private EventType getEventType(@NotNull Update update) {
        Optional<Message> normalMessage = Optional.ofNullable(update.getMessage());
        if (normalMessage.isPresent()) {
            return isOrderMessage(normalMessage.get()) ? EventType.ORDER_MESSAGE : EventType.NORMAL_MESSAGE;
        }


        return EventType.UNSUPPORTED_MESSAGE;
    }

    private boolean isOrderMessage(Message normalMessage) {
        return normalMessage.getText().startsWith("/");
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
