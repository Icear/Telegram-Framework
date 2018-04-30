package indi.telegramframwork;

import indi.telegramframwork.configuration.SpringConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 程序入口
 */
public class Application {
    private static boolean exitFlag = false;
    private Logger logger = LogManager.getLogger(Application.class);
    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        //启动Application
        Application application = new Application();

        //监听系统退出请求，主动退出
        Runtime.getRuntime().addShutdownHook(new Thread(() -> exitFlag = true));
        do {
            //保证程序不结束

        } while (!exitFlag);

    }

    private Application() {
        logger.info("Application start");

        //初始化Spring容器
        logger.info("Spring Container Initializing...");
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }

    @Override
    protected void finalize(){
        logger.info("Application exit");
    }
}