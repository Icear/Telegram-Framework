package indi.icear.framework.coolqhttpapi.model.submodel;

import java.io.Serializable;
import java.util.Objects;

/**
 * 匿名信息
 */
public class Anonymous implements Serializable {
    private Long id; //	number	匿名用户 ID

    private String name;//	string	匿名用户名称

    private String flag;//	string	匿名用户 flag，在调用禁言 API 时需要传入

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Anonymous{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anonymous)) return false;
        Anonymous anonymous = (Anonymous) o;
        return Objects.equals(id, anonymous.id) &&
                Objects.equals(name, anonymous.name) &&
                Objects.equals(flag, anonymous.flag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, flag);
    }
}
