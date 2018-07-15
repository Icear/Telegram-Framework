package indi.icear.framework.coolqhttpapi.model;


/**
 * 未定义的数据的统一储存结构
 */
public class UnknownEvent extends CoolQEvent {

    private String rowMessage;//手动填充，目前GSON无法自动填入raw数据

    public String getRowMessage() {
        return rowMessage;
    }

    public void setRowMessage(String rowMessage) {
        this.rowMessage = rowMessage;
    }

    @Override
    public String toString() {
        return "UnknownEvent{" +
                "rowMessage='" + rowMessage + '\'' +
                ", postType='" + postType + '\'' +
                ", time=" + time +
                ", selfId=" + selfId +
                '}';
    }
}
