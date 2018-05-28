package indi.icear.telegramframework.service.disquemq;

import indi.icear.telegramframework.service.disquemq.BeanFactory.DisqueMQBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * DisqueMQ服务组件
 */
@Component
public class DisqueMQService {

    @Value("${Disque.address}")
    private Set<String> address;

    @Bean
    public DisqueMQBeanFactory getJedisque() {
        DisqueMQBeanFactory disqueMQBeanFactory = new DisqueMQBeanFactory();
        disqueMQBeanFactory.setAddress(address);
        return disqueMQBeanFactory;
    }
}
