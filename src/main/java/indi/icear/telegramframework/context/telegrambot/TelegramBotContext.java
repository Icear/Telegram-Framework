package indi.icear.telegramframework.context.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import indi.icear.telegramframework.util.ProxyUtil;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Proxy;


/**
 * 用于创建TelegramBot的上下文
 */
@Component
public class TelegramBotContext {

    @Value("${telegramBot.proxy}")
    private boolean proxySwitch;
    @Value("${telegramBot.proxy.type}")
    private String proxyTypeString;
    @Value("${telegramBot.proxy.address}")
    private String proxyAddress;
    @Value("${telegramBot.proxy.port}")
    private int proxyPort;

    @Value("${telegramBot.token}")
    private String telegramBotToken;

    private Logger logger = LogManager.getLogger(TelegramBotContext.class);

    @Bean
    public TelegramBot getTelegramBot() {
        logger.info("connecting to telegram bot service via token...");
        logger.trace("telegramBot.token = " + telegramBotToken);
        TelegramBot.Builder telegramBotBuilder = new TelegramBot.Builder(telegramBotToken);
        if (proxySwitch) {
            logger.info("CoolQ.proxy on, proxy " + proxyTypeString + " to " + proxyAddress + ":" + proxyPort);

            Proxy proxy = ProxyUtil.generateProxy(proxyTypeString, proxyAddress, proxyPort);

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
                "proxySwitch=" + proxySwitch +
                ", proxyTypeString='" + proxyTypeString + '\'' +
                ", proxyAddress='" + proxyAddress + '\'' +
                ", proxyPort=" + proxyPort +
                ", telegramBotToken='" + telegramBotToken +
                '}';
    }
}
