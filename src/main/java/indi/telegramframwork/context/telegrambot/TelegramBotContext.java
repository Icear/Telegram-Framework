package indi.telegramframwork.context.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.net.Proxy;


/**
 * 用于创建TelegramBot的上下文
 */
@Component
public class TelegramBotContext {
    @Value("#{setting['telegramBot.token']}")
    private String telegramBotToken;
    @Value("#{setting['proxy']}")
    private boolean proxySwitch;
    @Value("#{setting['proxy.type']}")
    private String proxyTypeString;
    @Value("#{setting['proxy.address']}")
    private String proxyAddress;
    @Value("#{setting['proxy.port']}")
    private int port;

    private Logger logger = LogManager.getLogger(TelegramBotContext.class);

    @Bean
    public TelegramBot getTelegramBot() {
        logger.info("connecting to telegram bot service via token...");
        logger.trace("telegramBot.token = " + telegramBotToken);
        TelegramBot telegramBot;
        if (proxySwitch) {
            logger.info("proxy on, proxy " + proxyTypeString + " to " + proxyAddress + ":" + port);
            //转换proxyType
            Proxy.Type proxyType = Proxy.Type.HTTP;
            switch (proxyTypeString) {
                case "http":
                    proxyType = Proxy.Type.HTTP;
                    break;
                case "sock":
                    proxyType = Proxy.Type.SOCKS;
                    break;
                default:
                    logger.error("unsupported proxy type " + proxyTypeString + ", use default: HTTP");
            }

            telegramBot = new TelegramBot.Builder(telegramBotToken)
                    .okHttpClient(
                            new OkHttpClient.Builder()
                                    .proxy(new Proxy(proxyType, new InetSocketAddress(proxyAddress, port)))
                                    .build()
                    ).build();
        } else {
            telegramBot = new TelegramBot(telegramBotToken);
        }
        logger.info("telegram bot service connected");
        logger.debug("telegram bot service connect result: " + telegramBot);
        return telegramBot;
    }

    @Override
    public String toString() {
        return "TelegramBotContext{" +
                "telegramBotToken='" + telegramBotToken + '\'' +
                '}';
    }
}
