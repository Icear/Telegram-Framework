package indi.framework.coolqhttpapi.model.deserializer;

import com.google.gson.*;
import indi.framework.coolqhttpapi.model.CoolQEvent;
import indi.framework.coolqhttpapi.model.PrivateChatMessageEvent;

import java.lang.reflect.Type;

/**
 * CoolQ事件的JSON反序列化解析器
 */
public class CoolQEventDeserializer implements JsonDeserializer<CoolQEvent> {
    private Gson gson = new Gson();

    @Override
    public CoolQEvent deserialize(
            JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        CoolQEvent coolQEvent;

        //手动检查数据的类型来决定要转换的JSON对象

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String postType = jsonObject.get("post_type").getAsString();

        switch (postType) {
            case "message":
                String messageType = jsonObject.get("message_type").getAsString();
                switch (messageType) {
                    case "private":
                        coolQEvent = gson.fromJson(jsonElement, PrivateChatMessageEvent.class);
                        break;
                    default:
                        coolQEvent = gson.fromJson(jsonElement, CoolQEvent.class);
                }
                break;
            default:
                coolQEvent = gson.fromJson(jsonElement, CoolQEvent.class);
        }

        return coolQEvent;
    }
}
