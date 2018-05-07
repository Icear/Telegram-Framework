package indi.framework.coolqhttpapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import indi.framework.coolqhttpapi.model.CoolQEvent;
import indi.framework.coolqhttpapi.model.deserializer.CoolQEventDeserializer;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * 用于CoolQ的Event接口的WebSocket监听器
 */
public class CoolQEventSocketListener extends WebSocketListener {
    private CoolQEventListener coolQEventListener;
    private Gson gson = new GsonBuilder().registerTypeAdapter(CoolQEvent.class, new CoolQEventDeserializer()).create();

    public CoolQEventSocketListener(@NotNull CoolQEventListener coolQEventListener) {
        this.coolQEventListener = coolQEventListener;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        CoolQEvent coolQEvent = gson.fromJson(text, CoolQEvent.class);
        coolQEventListener.handleEvent(coolQEvent);
    }
}
