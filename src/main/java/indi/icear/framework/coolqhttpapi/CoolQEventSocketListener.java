package indi.icear.framework.coolqhttpapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import indi.icear.framework.coolqhttpapi.model.CoolQEvent;
import indi.icear.framework.coolqhttpapi.util.JacksonUtil;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 用于CoolQ的Event接口的WebSocket监听器
 */
public class CoolQEventSocketListener extends WebSocketListener {
    private CoolQEventListener coolQEventListener;
    private ObjectMapper objectMapper = JacksonUtil.getObjectMapper();

    public CoolQEventSocketListener(@NotNull CoolQEventListener coolQEventListener) {
        this.coolQEventListener = coolQEventListener;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        CoolQEvent coolQEvent;
        try {
            coolQEvent = objectMapper.readValue(text, CoolQEvent.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        coolQEventListener.handleEvent(coolQEvent);
    }
}
