package indi.icear.framework.coolqhttpapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import indi.icear.framework.coolqhttpapi.model.submodel.Anonymous;
import indi.icear.framework.coolqhttpapi.model.submodel.Message;
import org.jetbrains.annotations.Nullable;

/**
 * 群消息
 */
public class GroupChatMessageEvent extends CoolQEvent {

    @JsonProperty("message_type")
    private String messageType; //group	消息类型

    @JsonProperty("sub_type")
    private String subType; //normal、anonymous、notice	消息子类型，正常消息是 normal，匿名消息是 anonymous，系统提示（如「管理员已禁止群内匿名聊天」）是 notice

    @JsonProperty("message_id")
    private Long messageId; //number 消息 ID

    @JsonProperty("group_id")
    private Long groupId; //number 群号

    @JsonProperty("user_id")
    private Long userId; //number 发送者 QQ 号

    @JsonProperty("anonymous")
    private @Nullable Anonymous anonymous;  //	-	匿名信息，如果不是匿名消息则为 null

    @JsonProperty("message")
    private Message message; //message	-	消息内容

    @JsonProperty("raw_message")
    private String rawMessage;  //string	-	原始消息内容

    @JsonProperty("font")
    private Long font;  //number	-	字体


}
