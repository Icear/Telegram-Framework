package indi.telegramframework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 程序入口
 */
public class Application {
    private static volatile boolean exitFlag = false;
    private Logger logger = LogManager.getLogger(Application.class);
    private ConfigurableApplicationContext context;

    private Application() {
        logger.info("Application start");

        //初始化Spring容器
        logger.debug("initializing Spring Container...");
//        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.debug("Spring Container Initialized");
    }

    public static void main(String[] args) {
        //启动Application
        Application application = new Application();

        //监听系统退出请求，主动退出
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            application.exit();
            exitFlag = true;
        }));
        //保证程序不结束

        while (!exitFlag) {
            //保证程序不结束
        }
        System.exit(0);
    }

    private void exit() {
        logger.info("exiting...");
        logger.debug("close Spring Container...");
        context.close();
        logger.debug("Spring Container closed");
        logger.info("Application exit");
    }

    @Override
    protected void finalize() {
        logger.info("Application exit");
    }
}
