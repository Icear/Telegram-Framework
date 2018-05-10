package indi.framework.coolqhttpapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * 酷Q的HTTP-API接口实现
 */
public class CoolQ {
    private String wsHost;
    private int wsPort;
    private String accessToken;
    private OkHttpClient okHttpClient;

    private CoolQ() {

    }

    /**
     * 添加CoolQ上报事件监听器
     *
     * @param coolQEventListener 事件监听器
     */
    public void addEventListener(@NotNull CoolQEventListener coolQEventListener) {
        connectToEventAPIViaWebSocket(coolQEventListener);
    }

    /**
     * 连接到WebSocket模式的事件API
     *
     * @param coolQEventListener 事件监听器
     */
    private void connectToEventAPIViaWebSocket(@NotNull CoolQEventListener coolQEventListener) {
        CoolQEventSocketListener coolQEventSocketListener = new CoolQEventSocketListener(coolQEventListener);
        Request request = new Request.Builder()
                .url(String.join("", "ws://", wsHost, String.valueOf(wsPort)))
                .header("Authorization", accessToken)//加入验证头
                .build();
        okHttpClient.newWebSocket(request, coolQEventSocketListener);
    }

    public static class Builder {
        private String accessToken;
        private String wsHost;
        private int wsPort;
        private OkHttpClient customOkHttpClient;

        public Builder() {
        }

        /**
         * 设置与CoolQ连接的验证Token
         *
         * @param accessToken Token
         */
        public Builder setAccessToken(@NotNull String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        /**
         * 设置与CoolQ连接的Host，正向WebSocket模式必填
         *
         * @param wsHost 主机地址
         */
        public Builder setWsHost(@NotNull String wsHost) {
            this.wsHost = wsHost;
            return this;
        }

        /**
         * 设置与CoolQ连接的port，正向WebSocket模式，必填
         *
         * @param wsPort 端口
         */
        public Builder setWsPort(int wsPort) {
            this.wsPort = wsPort;
            return this;
        }

        /**
         * 使用自定义的OkHttpClient，可选
         *
         * @param customOkHttpClient 自定义的OkHttpClient
         */
        public Builder setCustomOkHttpClient(@NotNull OkHttpClient customOkHttpClient) {
            this.customOkHttpClient = customOkHttpClient;
            return this;
        }

        /**
         * 基于填入的配置生成CoolQ实例
         * 配置要求：
         * wsHost 必填 {@link #setWsHost(String)}
         * wsPert 必填 {@link #setWsPort(int)}
         * accessToken 必填 {@link #setAccessToken(String)}
         *
         * @return CoolQ实例
         */
        public CoolQ build() {
            CoolQ coolQ = new CoolQ();
            coolQ.accessToken = accessToken;
            coolQ.wsHost = wsHost;
            coolQ.wsPort = wsPort;

//            //如果设置了EventListener则通过WebSocket模式连接到事件API并监听
//            Optional<CoolQEventListener> eventListenerOptional = Optional.ofNullable(coolQEventListener);
//            eventListenerOptional.ifPresent(coolQ::connectToEventAPIViaWebSocket);

            //如果未设置自定义OkHttpClient则使用默认的
            Optional<OkHttpClient> okHttpClientOptional = Optional.ofNullable(customOkHttpClient);
            coolQ.okHttpClient = okHttpClientOptional.orElse(new OkHttpClient());

            return coolQ;
        }
    }


}
