package indi.telegramframework.context.coolq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * 用于CoolQ的上下文
 */
@Component
public class CoolQContext {
    private Logger logger = LogManager.getLogger(CoolQContext.class);

    public CoolQContext() {
        //连接到
    }
}
