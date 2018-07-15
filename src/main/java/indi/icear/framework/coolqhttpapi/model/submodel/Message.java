package indi.icear.framework.coolqhttpapi.model.submodel;

import java.io.Serializable;
import java.util.Objects;

/**
 * 处理后的消息数据
 */
public class Message implements Serializable {

    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(string, message.string);
    }

    @Override
    public int hashCode() {

        return Objects.hash(string);
    }
}
