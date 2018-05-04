package indi.framework.coolqhttpapi.model;

/**
 * CoolQ事件基础接口
 */
public class CoolQEvent {
    //post_type
    protected String postType;

    //time
    protected long time;

    //self_id
    protected long selfId;

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
