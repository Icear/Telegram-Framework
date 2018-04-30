package indi.telegramframwork.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * Spring框架的配置类
 */
@Configuration
@ComponentScans({
        @ComponentScan("indi.telegramframwork.context"),
        @ComponentScan("indi.telegramframwork.plugin")
})

public class SpringConfiguration {
    private Logger logger = LogManager.getLogger(SpringConfiguration.class);

    public SpringConfiguration() {

    }
}
