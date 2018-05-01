package indi.telegramframwork.context.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import indi.telegramframwork.context.telegrambot.event.TelegramBotEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * TelegramBot事件的监听者，同时负责发布全局TelegramBot事件
 */
@Component
public class TelegramBotEventListener implements ApplicationEventPublisherAware {
    private final TelegramBot telegramBot;
    private Logger logger = LogManager.getLogger(TelegramBotEventListener.class);
    @Value("#{setting['telegramBot.connectWay']}")
    private String connectWay;

    @Autowired
    public TelegramBotEventListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        //注册更新监听器，目前使用Update监听器来更新事件，同时通过ApplicationEventPublisher来发布事件
        //TODO 加入WebHook支持
        //在接收到Update事件后将每一个事件转换为TelegramBotEvent并发布

        switch (connectWay) {
            case "UpdateListener":
                telegramBot.setUpdatesListener(updateList -> {
                    updateList
                            .stream()
                            .map(TelegramBotEvent::new)
                            .forEach(applicationEventPublisher::publishEvent);
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;//返回所有事件已处理
                });
                break;
            default:
                logger.error("unsupported connectWay: " + connectWay + ", event will not be check");
        }
    }
}
