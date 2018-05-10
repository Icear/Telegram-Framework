package indi.telegramframework.context.telegrambot;

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

    @Value("#{setting['telegramBot.proxy']}")
    private boolean proxySwitch;
    @Value("#{setting['telegramBot.proxy.type']}")
    private String proxyTypeString;
    @Value("#{setting['telegramBot.proxy.address']}")
    private String proxyAddress;
    @Value("#{setting['telegramBot.proxy.port']}")
    private int port;

    @Value("#{setting['telegramBot.token']}")
    private String telegramBotToken;

    private Logger logger = LogManager.getLogger(TelegramBotContext.class);

    @Bean
    public TelegramBot getTelegramBot() {
        logger.info("connecting to telegram bot service via token...");
        logger.trace("telegramBot.token = " + telegramBotToken);
        TelegramBot.Builder telegramBotBuilder = new TelegramBot.Builder(telegramBotToken);
        if (proxySwitch) {
            logger.info("CoolQ.proxy on, proxy " + proxyTypeString + " to " + proxyAddress + ":" + port);

            Proxy proxy;
            //转换proxyType
            switch (proxyTypeString) {
                case "http":
                    proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, port));
                    break;
                case "sock":
                    proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyAddress, port));
                    break;
                default:
                    proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, port));
                    logger.error("unsupported proxy type " + proxyTypeString + ", use default: HTTP");
            }

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
                ", port=" + port +
                ", telegramBotToken='" + telegramBotToken +
                '}';
    }
}
