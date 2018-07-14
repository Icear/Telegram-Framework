package indi.icear.telegramframework.telegrambot;

import indi.icear.telegramframework.configuration.TelegramBotProperties;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;


/**
 * 用于创建TelegramBot的上下文
 */
@Component
@EnableConfigurationProperties(TelegramBotProperties.class)
public class TelegramBotContext {

    private static Logger logger = LogManager.getLogger(TelegramBotContext.class);

    @Bean
    public TelegramBot telegramBot(TelegramBotProperties properties, TelegramBotEventDispatcher telegramBotEventDispatcher) {
        logger.info("connecting to TelegramBot service via accessToken...");
        logger.debug("TelegramBot.Token = " + properties.getToken());
        if (properties.getProxy() == null) {
            return new TelegramBot(properties.getName(), properties.getToken(), telegramBotEventDispatcher);
        }

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        HttpHost httpHost = new HttpHost(properties.getProxy().getAddress(), properties.getProxy().getPort());
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(httpHost)
                .setAuthenticationEnabled(false)
                .build();
        botOptions.setRequestConfig(requestConfig);
        botOptions.setCredentialsProvider(new BasicCredentialsProvider());
        botOptions.setHttpProxy(httpHost);
        return new TelegramBot(botOptions, properties.getName(), properties.getToken(), telegramBotEventDispatcher);
    }


}
