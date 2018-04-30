package indi.telegramframwork.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.util.Arrays;

/**
 * Spring框架的配置类
 */
@Configuration
@ComponentScans({
        @ComponentScan("indi.telegramframwork.context"),
        @ComponentScan("indi.telegramframwork.plugin"),
})
@Deprecated
public class SpringConfiguration {
    private Logger logger = LogManager.getLogger(SpringConfiguration.class);
    @Autowired
    private PropertiesFactoryBean propertiesFactoryBean;


    public SpringConfiguration() {
        logger.info("Spring Container Initializing...");
    }

    @Bean(value = "configProperties", autowire = Autowire.BY_TYPE)
    public PropertiesFactoryBean getPropertiesFactoryBean() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new DefaultResourceLoader().getResource("classpath:application.properties"));
        return propertiesFactoryBean;
    }

    public void setPropertiesFactoryBean(PropertiesFactoryBean propertiesFactoryBean) {
        this.propertiesFactoryBean = propertiesFactoryBean;
    }

    @Bean(value = "propertyConfigurer", autowire = Autowire.BY_TYPE)
    public PreferencesPlaceholderConfigurer getPreferencesPlaceholderConfigurer() throws IOException {

        PreferencesPlaceholderConfigurer preferencesPlaceholderConfigurer = new PreferencesPlaceholderConfigurer();
        preferencesPlaceholderConfigurer.setProperties(propertiesFactoryBean.getObject());
        return preferencesPlaceholderConfigurer;
    }
}
