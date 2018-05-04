package indi.framework.coolqhttpapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 酷Q的HTTP-API接口实现
 */
public class CoolQ {
    private String wsHost;
    private String wsPort;
    private String accessToken;
    private OkHttpClient okHttpClient;


    public CoolQ(String wsHost, String wsPort, String accessToken) {
        this.wsHost = wsHost;
        this.wsPort = wsPort;
        this.accessToken = accessToken;
        okHttpClient = new OkHttpClient();
    }

    private void connectToEventAPI() {
        CoolQEventSocketListener listener = new CoolQEventSocketListener();
        Request request = new Request.Builder()
                .url(String.join("", "ws://", wsHost, wsPort))
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newWebSocket(request, listener);
    }


}
