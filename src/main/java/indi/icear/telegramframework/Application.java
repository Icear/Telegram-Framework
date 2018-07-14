package indi.icear.telegramframework;

import indi.icear.telegramframework.configuration.TelegramBotConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 程序入口
 */
@SpringBootApplication
@Import(TelegramBotConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
