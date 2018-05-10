package indi.framework.coolqhttpapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * CoolQ事件基础接口
 */
public class CoolQEvent {

    @SerializedName("post_type")
    String postType;

    @SerializedName("time")
    long time;

    @SerializedName("self_id")
    long selfId;

    public CoolQEvent(String postType, long time, long selfId) {
        this.postType = postType;
        this.time = time;
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
