package cuit.pymjl.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:38
 **/
public class Request<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -5809782578272943999L;
    /**
     * 选项
     */
    private Integer option;
    /**
     * 数据
     */
    private T data;

    /**
     * 第几级菜单的指令
     */
    private Integer group;

    public Request() {
    }

    public Request(Integer option, T data, Integer group) {
        this.option = option;
        this.data = data;
        this.group = group;
    }

    public Request(Integer option, Integer group) {
        this.option = option;
        this.data = null;
        this.group = group;
    }

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Request{" +
                "option=" + option +
                ", data=" + data +
                ", group=" + group +
                '}';
    }
}
