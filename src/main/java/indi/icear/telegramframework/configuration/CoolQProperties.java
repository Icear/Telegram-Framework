package indi.icear.telegramframework.configuration;

import indi.icear.telegramframework.util.ProxyUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coolq")
public class CoolQProperties {

    private ProxyUtil.Proxy proxy;
    private String accessToken;
    private String webSocketAddress;
    private int webSocketPort;

    public ProxyUtil.Proxy getProxy() {
        return proxy;
    }

    public void setProxy(ProxyUtil.Proxy proxy) {
        this.proxy = proxy;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getWebSocketAddress() {
        return webSocketAddress;
    }

    public void setWebSocketAddress(String webSocketAddress) {
        this.webSocketAddress = webSocketAddress;
    }

    public int getWebSocketPort() {
        return webSocketPort;
    }

    public void setWebSocketPort(int webSocketPort) {
        this.webSocketPort = webSocketPort;
    }


}
