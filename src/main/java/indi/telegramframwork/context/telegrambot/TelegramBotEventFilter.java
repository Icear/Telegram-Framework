package indi.telegramframwork.context.telegrambot;

import indi.telegramframwork.context.telegrambot.event.TelegramBotEvent;

/**
 * Bot事件的过滤器接口
 * <p>
 * 实现接口后使用@Compoment或@Bean注解进入Spring容器管理状态即可生效
 * 过滤器顺序不确定
 */
@FunctionalInterface
public interface TelegramBotEventFilter {
    /**
     * 检查事件
     *
     * @param telegramBotEvent 被检查的事件
     * @return 事件是否允许通过
     */
    boolean filterEvent(TelegramBotEvent telegramBotEvent);
}
