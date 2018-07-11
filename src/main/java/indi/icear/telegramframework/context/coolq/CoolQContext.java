package indi.icear.telegramframework.context.coolq;

import indi.icear.framework.coolqhttpapi.CoolQ;
import indi.icear.telegramframework.configuration.CoolQConfig;
import indi.icear.telegramframework.util.ProxyUtil;
import okhttp3.OkHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.Proxy;


/**
 * 用于CoolQ的上下文
 */
@Component
@EnableConfigurationProperties(CoolQConfig.class)
public class CoolQContext {
    private Logger logger = LogManager.getLogger(CoolQContext.class);

    private CoolQConfig properties;

    public CoolQContext(CoolQConfig properties) {
        this.properties = properties;
    }



    @Bean
    private CoolQ getCoolQ() {
        logger.info("connecting to CoolQ service via accessToken...");
        logger.debug("CoolQ.accessToken = " + properties.getAccessToken());
        CoolQ.Builder coolQBuilder = new CoolQ.Builder();
        if (properties.getProxy() != null) {
            logger.info(
                    "CoolQ.proxy on, proxy "
                            + properties.getProxy().getType().getText() + " to "
                            + properties.getProxy().getAddress() + ":"
                            + properties.getProxy().getPort());
            Proxy proxy = ProxyUtil.generateProxy(
                    properties.getProxy().getType(),
                    properties.getProxy().getAddress(),
                    properties.getProxy().getPort());
            coolQBuilder.setCustomOkHttpClient(
                    new OkHttpClient.Builder()
                            .proxy(proxy)
                            .build()
            );
        }

        CoolQ coolQ = coolQBuilder
                .setAccessToken(properties.getAccessToken())
                .setWsHost(properties.getWebSocketAddress())
                .setWsPort(properties.getWebSocketPort())
                .build();
        logger.info("CoolQ service connected");
        logger.debug("CoolQ service connect result: " + coolQ);

        return coolQ;
    }


}
