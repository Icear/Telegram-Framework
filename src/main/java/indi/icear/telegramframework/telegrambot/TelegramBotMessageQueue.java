package indi.icear.telegramframework.telegrambot;


/**
 * TelegramBot消息队列常量
 */
public class TelegramBotMessageQueue {


    /**
     * 统一发布通道，所有的消息会在这里发布
     */
    public static final String universityMessageDestination = "telegramBot.universityMessage";

    /**
     * 命令消息通道的标记开头
     */
    public static final String orderMessageDestinationHeader = "telegramBot.orderMessageQueue.";

    /**
     * 普通消息通道
     */
    public static final String normalMessageDestination = "telegramBot.normalMessageQueue";

    /**
     * 回复类消息通道
     */
    public static final String replyMessageDestination = "telegramBot.replyMessageQueue";
}
