package indi.icear.framework.coolqhttpapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

/**
 * 私聊消息
 */
public class PrivateChatMessageEvent extends CoolQEvent {

    @SerializedName("message_type")
    private String messageType;
    @SerializedName("sub_type")
    private String subType;
    @SerializedName("message_id")
    private long messageId;
    @SerializedName("user_id")
    private long user_id;
    @SerializedName("message")
    private Set<String> message;
    @SerializedName("font")
    private long font;

    public PrivateChatMessageEvent(
            String postType, long time, long selfId, String messageType, String subType,
            long messageId, long user_id, Set<String> message, long font) {
        super(postType, time, selfId);
        this.messageType = messageType;
        this.subType = subType;
        this.messageId = messageId;
        this.user_id = user_id;
        this.message = message;
        this.font = font;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getSubType() {
        return subType;
    }

    public long getMessageId() {
        return messageId;
    }

    public long getUser_id() {
        return user_id;
    }

    public Set<String> getMessage() {
        return message;
    }

    public long getFont() {
        return font;
    }

    @Override
    public String toString() {
        return "PrivateChatMessageEvent{" +
                "messageType='" + messageType + '\'' +
                ", subType='" + subType + '\'' +
                ", messageId=" + messageId +
                ", user_id=" + user_id +
                ", message=" + message +
                ", font=" + font +
                ", postType='" + postType + '\'' +
                ", time=" + time +
                ", selfId=" + selfId +
                '}';
    }
}
