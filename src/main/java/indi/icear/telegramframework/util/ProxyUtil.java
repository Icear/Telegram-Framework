package indi.icear.telegramframework.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import java.net.InetSocketAddress;


public class ProxyUtil {
    private static Logger logger = LogManager.getLogger(ProxyUtil.class);

    @NotNull
    public static java.net.Proxy generateProxy(Proxy.Type proxyType, String proxyAddress, int port) {
        java.net.Proxy proxy;
        //转换proxyType
        switch (proxyType) {
            case HTTP:
                proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, port));
                break;
            case SOCKS:
                proxy = new java.net.Proxy(java.net.Proxy.Type.SOCKS, new InetSocketAddress(proxyAddress, port));
                break;
            default:
                proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, port));
                logger.error("unsupported proxy type " + proxyType + ", use default: HTTP");
        }
        return proxy;
    }


    public static class Proxy {
        /**
         * proxy.type: HTTP/sock
         */
        private Proxy.Type type;
        private String address;
        private int port;

        public Proxy.Type getType() {
            return type;
        }

        public void setType(Proxy.Type type) {
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public enum Type {
            HTTP("HTTP"), SOCKS("SOCKS");

            private String text;

            Type(String text) {
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
                return "Type{" +
                        "text='" + text + '\'' +
                        '}';
            }
        }
    }
}
