package indi.telegramframework.context.coolq;

import indi.framework.coolqhttpapi.CoolQ;
import indi.telegramframework.util.ProxyUtil;
import okhttp3.OkHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.Proxy;


/**
 * 用于CoolQ的上下文
 */
@Component
public class CoolQContext {
    private Logger logger = LogManager.getLogger(CoolQContext.class);

    @Value("#{setting['CoolQ.proxy']}")
    private boolean proxySwitch;
    @Value("#{setting['CoolQ.proxy.type']}")
    private String proxyTypeString;
    @Value("#{setting['CoolQ.proxy.address']}")
    private String proxyAddress;
    @Value("#{setting['CoolQ.proxy.port']}")
    private int proxyPort;

    @Value("#{setting['CoolQ.accessToken']}")
    private String accessToken;

    @Value("#{setting['CoolQ.webSocketHost']}")
    private String wsHost;

    @Value("#{setting['CoolQ.webSocketHost']}")
    private int wsPort;

    @Bean
    private CoolQ getCoolQ() {
        logger.info("connecting to CoolQ service via accessToken...");
        logger.trace("CoolQ.accessToken = " + accessToken);
        CoolQ.Builder coolQBuilder = new CoolQ.Builder();
        if (proxySwitch) {
            logger.info("CoolQ.proxy on, proxy " + proxyTypeString + " to " + proxyAddress + ":" + proxyPort);
            Proxy proxy = ProxyUtil.generateProxy(proxyTypeString, proxyAddress, proxyPort);
            coolQBuilder.setCustomOkHttpClient(
                    new OkHttpClient.Builder()
                            .proxy(proxy)
                            .build()
            );
        }

        CoolQ coolQ = coolQBuilder
                .setAccessToken(accessToken)
                .setWsHost(wsHost)
                .setWsPort(wsPort)
                .build();
        logger.info("CoolQ service connected");
        logger.debug("CoolQ service connect result: " + coolQ);

        return coolQ;
    }


}
