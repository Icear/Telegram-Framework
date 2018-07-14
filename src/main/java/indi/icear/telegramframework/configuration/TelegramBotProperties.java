package indi.icear.telegramframework.configuration;

import indi.icear.telegramframework.util.ProxyUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram-bot")
public class TelegramBotProperties {
    private ProxyUtil.Proxy proxy;
    private String token;
    private ConnectWay connectWay;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProxyUtil.Proxy getProxy() {
        return proxy;
    }

    public void setProxy(ProxyUtil.Proxy proxy) {
        this.proxy = proxy;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ConnectWay getConnectWay() {
        return connectWay;
    }

    public void setConnectWay(ConnectWay connectWay) {
        this.connectWay = connectWay;
    }

    public enum ConnectWay {
        updateListener("updateListener"), webHooking("webHooking");

        private String text;

        ConnectWay(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "ConnectWay{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TelegramBotProperties{" +
                "proxy=" + proxy +
                ", token='" + token + '\'' +
                ", connectWay=" + connectWay +
                ", name='" + name + '\'' +
                '}';
    }
}
