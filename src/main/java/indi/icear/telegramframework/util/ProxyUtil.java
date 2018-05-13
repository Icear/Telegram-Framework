package indi.icear.telegramframework.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxyUtil {
    private static Logger logger = LogManager.getLogger(ProxyUtil.class);

    @NotNull
    public static Proxy generateProxy(String proxyTypeString, String proxyAddress, int port) {
        Proxy proxy;
        //转换proxyType
        switch (proxyTypeString) {
            case "http":
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, port));
                break;
            case "sock":
                proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyAddress, port));
                break;
            default:
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, port));
                logger.error("unsupported proxy type " + proxyTypeString + ", use default: HTTP");
        }
        return proxy;
    }
}
