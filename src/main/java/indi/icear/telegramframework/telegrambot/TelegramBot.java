package indi.icear.telegramframework.telegrambot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


public class TelegramBot extends TelegramLongPollingBot {

    private Logger logger = LogManager.getLogger(TelegramBot.class);

    private String userName;

    private String botToken;

    private TelegramBotEventDispatcher telegramBotEventDispatcher;

    TelegramBot(String userName, String botToken, TelegramBotEventDispatcher telegramBotEventDispatcher) {
        this.userName = userName;
        this.botToken = botToken;
        this.telegramBotEventDispatcher = telegramBotEventDispatcher;
    }

    TelegramBot(DefaultBotOptions options, String userName, String botToken, TelegramBotEventDispatcher telegramBotEventDispatcher) {
        super(options);
        this.userName = userName;
        this.botToken = botToken;
        this.telegramBotEventDispatcher = telegramBotEventDispatcher;
    }


    @Override
    public void onUpdateReceived(Update update) {
        telegramBotEventDispatcher.dispatchEvent(update);
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
