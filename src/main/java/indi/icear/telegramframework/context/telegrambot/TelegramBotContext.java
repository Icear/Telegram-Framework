package indi.icear.telegramframework.context.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import indi.icear.telegramframework.configuration.TelegramBotConfig;
import indi.icear.telegramframework.util.ProxyUtil;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Proxy;


/**
 * 用于创建TelegramBot的上下文
 */
@Component
@EnableConfigurationProperties(TelegramBotConfig.class)
public class TelegramBotContext {

    private TelegramBotConfig properties;

    private Logger logger = LogManager.getLogger(TelegramBotContext.class);

    public TelegramBotContext(TelegramBotConfig properties) {
        this.properties = properties;
    }

    @Bean
    public TelegramBot getTelegramBot() {
        logger.info("connecting to telegram bot service via token...");
        logger.trace("telegramBot.token = " + properties.getToken());
        TelegramBot.Builder telegramBotBuilder = new TelegramBot.Builder(properties.getToken());
        if (properties.getProxy() != null) {
            logger.info(
                    "CoolQ.proxy on, proxy "
                            + properties.getProxy().getType().getText() + " to "
                            + properties.getProxy().getAddress() + ":"
                            + properties.getProxy().getPort());

            Proxy proxy = ProxyUtil.generateProxy(
                    properties.getProxy().getType(),
                    properties.getProxy().getAddress(),
                    properties.getProxy().getPort());

            telegramBotBuilder.okHttpClient(
                    new OkHttpClient.Builder()
                            .proxy(proxy)
                            .build()
            );
        }

        TelegramBot telegramBot = telegramBotBuilder.build();
        logger.info("telegram bot service connected");
        logger.debug("telegram bot service connect result: " + telegramBot);
        return telegramBot;
    }

    @Override
    public String toString() {
        return "TelegramBotContext{" +
                "properties=" + properties +
                '}';
    }
}
