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

import java.util.Set;
import java.util.function.Predicate;

/**
 * TelegramBot事件的监听者，同时负责发布全局TelegramBot事件
 */
@Component
public class TelegramBotEventListener implements ApplicationEventPublisherAware {
    private final TelegramBot telegramBot;
    private Logger logger = LogManager.getLogger(TelegramBotEventListener.class);

    @Value("#{setting['telegramBot.connectWay']}")
    private String connectWay;

    private final Set<TelegramBotEventFilter> telegramBotEventFilterSet;

    @Autowired
    public TelegramBotEventListener(TelegramBot telegramBot, Set<TelegramBotEventFilter> telegramBotEventFilterSet) {
        this.telegramBot = telegramBot;
        //使用Spring注入获得所有的Filter实现
        this.telegramBotEventFilterSet = telegramBotEventFilterSet;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        //注册更新监听器，目前使用Update监听器来更新事件，同时通过ApplicationEventPublisher来发布事件
        //TODO 加入WebHook支持


        logger.info("add telegram bot update listener via " + connectWay + "...");
        switch (connectWay) {
            case "UpdateListener":
                telegramBot.setUpdatesListener(updateList -> {
                    logger.debug("receive update events");
                    logger.trace("update events: " + updateList.toString());
                    //接收到Update事件后
                    //将Update事件转换为TelegramBotEvent
                    //为每一个事件调用telegramBotFilter的filter函数进行过滤
                    //保留通过过滤的事件
                    //发布
                    updateList
                            .stream()
                            .map(TelegramBotEvent::new)
                            .filter(telegramBotEvent -> telegramBotEventFilterSet
                                    .stream()
                                    .map(telegramBotEventFilter -> telegramBotEventFilter.filterEvent(telegramBotEvent))
                                    .noneMatch(Predicate.isEqual(false)))
                            .forEach(applicationEventPublisher::publishEvent);
                    logger.debug("dispatch event finished");
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;//返回所有事件已处理
                });
                logger.info("start listening telegram bot event");
                break;
            default:
                logger.error("unsupported connectWay: " + connectWay + ", event will not be check");
        }
    }
}
