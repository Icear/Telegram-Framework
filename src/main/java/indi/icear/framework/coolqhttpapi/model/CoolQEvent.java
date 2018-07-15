package indi.icear.framework.coolqhttpapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * CoolQ事件基础接口
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) //开启反序列多子类实体的能力
public class CoolQEvent implements Serializable {

    @JsonProperty("post_type")
    String postType;

    @JsonProperty("time")
    long time;

    @JsonProperty("self_id")
    long selfId;

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setSelfId(long selfId) {
        this.selfId = selfId;
    }

    public String getPostType() {
        return postType;
    }

    public long getTime() {
        return time;
    }

    public long getSelfId() {
        return selfId;
    }

    @Override
    public String toString() {
        return "CoolQEvent{" +
                "postType='" + postType + '\'' +
                ", time=" + time +
                ", selfId=" + selfId +
                '}';
    }
}
