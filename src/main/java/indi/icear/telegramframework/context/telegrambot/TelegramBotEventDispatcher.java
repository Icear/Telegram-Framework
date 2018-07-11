package indi.icear.telegramframework.context.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import indi.icear.telegramframework.configuration.TelegramBotConfig;
import indi.icear.telegramframework.context.telegrambot.event.TelegramBotEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Predicate;

import static indi.icear.telegramframework.configuration.TelegramBotConfig.ConnectWay.updateListener;

/**
 * TelegramBot事件的监听者，同时负责发布全局TelegramBot事件
 */
@Component
@EnableConfigurationProperties(TelegramBotConfig.class)
public class TelegramBotEventDispatcher implements ApplicationEventPublisherAware {
    private Logger logger = LogManager.getLogger(TelegramBotEventDispatcher.class);

    private TelegramBotConfig properties;

    private final Set<TelegramBotEventFilter> telegramBotEventFilterSet;
    private final TelegramBot telegramBot;
    private ApplicationEventPublisher applicationEventPublisher;

    public TelegramBotEventDispatcher(TelegramBotConfig properties, TelegramBot telegramBot, Set<TelegramBotEventFilter> telegramBotEventFilterSet) {
        this.properties = properties;
        this.telegramBot = telegramBot;
        //使用Spring注入获得所有的Filter实现
        this.telegramBotEventFilterSet = telegramBotEventFilterSet;
        logger.debug("telegram bot event filter list: " + "\n"
                + "size: " + telegramBotEventFilterSet.size() + "\n"
                + "entity" + telegramBotEventFilterSet);
    }

    @Override
    public void setApplicationEventPublisher(@NotNull ApplicationEventPublisher applicationEventPublisher) {
        //TODO 存在一个问题，同一个事件会被触发两次
        this.applicationEventPublisher = applicationEventPublisher;
        addBotUpdatesListener();
    }

    private void addBotUpdatesListener() {
        //注册更新监听器，目前使用Update监听器来更新事件，同时通过ApplicationEventPublisher来发布事件
        //TODO 加入WebHook支持

        logger.info("add telegram bot update listener via " + properties.getConnectWay() + "...");
        switch (properties.getConnectWay()) {
            case updateListener:
                telegramBot.setUpdatesListener(updateList -> {
                    logger.debug("receive update events" + " ,total " + updateList.size());
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
                logger.error("unsupported connectWay: " + properties.getConnectWay() + ", event will not be check");
        }
    }

    @Override
    public String toString() {
        return "TelegramBotEventDispatcher{" +
                "properties=" + properties +
                ", telegramBotEventFilterSet=" + telegramBotEventFilterSet +
                ", telegramBot=" + telegramBot +
                ", applicationEventPublisher=" + applicationEventPublisher +
                '}';
    }
}
