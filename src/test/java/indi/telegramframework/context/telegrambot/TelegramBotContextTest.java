package indi.telegramframework.context.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import indi.telegramframework.SpringTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class TelegramBotContextTest extends SpringTestBase {

    @Autowired
    private TelegramBot telegramBot;

    @Test
    public void getTelegramBot() {
        GetUpdatesResponse updatesResponse = telegramBot.execute(new GetUpdates().limit(100).offset(0).timeout(0));
        assertTrue(updatesResponse.isOk());
    }
}