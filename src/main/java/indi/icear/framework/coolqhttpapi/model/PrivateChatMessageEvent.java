package indi.icear.framework.coolqhttpapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import indi.icear.framework.coolqhttpapi.model.submodel.Message;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 私聊消息
 */
public class PrivateChatMessageEvent extends CoolQEvent {

    @JsonProperty("message_type")
    private String messageType;  //string	private	消息类型
    @JsonProperty("sub_type")
    private String subType;  //string	friend、group、discuss、other	消息子类型，如果是好友则是 friend，如果从群或讨论组来的临时会话则分别是 group、discuss
    @JsonProperty("message_id")
    private Long messageId;  //number	-	消息 ID
    @JsonProperty("user_id")
    private Long user_id;  //number	-	发送者 QQ 号
    @JsonProperty("message")
    private List<Message> message;
    @JsonProperty("font")
    private Long font;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public Long getFont() {
        return font;
    }

    public void setFont(Long font) {
        this.font = font;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateChatMessageEvent)) return false;
        PrivateChatMessageEvent that = (PrivateChatMessageEvent) o;
        return Objects.equals(messageType, that.messageType) &&
                Objects.equals(subType, that.subType) &&
                Objects.equals(messageId, that.messageId) &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(message, that.message) &&
                Objects.equals(font, that.font);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageType, subType, messageId, user_id, message, font);
    }
}
