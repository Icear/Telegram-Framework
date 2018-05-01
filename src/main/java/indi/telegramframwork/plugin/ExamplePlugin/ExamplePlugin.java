package indi.telegramframwork.plugin.ExamplePlugin;

import indi.telegramframwork.context.telegrambot.TelegramBotEventFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ExamplePlugin {

    @Bean
    public TelegramBotEventFilter addCustomTelegramBotEventFilter() {
        return telegramBotEvent -> true;
    }
}
