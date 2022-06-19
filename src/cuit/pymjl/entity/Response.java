package cuit.pymjl.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:40
 **/
public class Response<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -5809782578272943673L;
    /**
     * 是否成功
     */
    private Boolean succeed;
    /**
     * 数据
     */
    private T data;
    /**
     * 消息
     */
    private String message;

    @Override
    public String toString() {
        return "Response{" +
                "succeed=" + succeed +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public Response() {
    }

    public Response(Boolean succeed, T data, String message) {
        this.succeed = succeed;
        this.data = data;
        this.message = message;
    }

    public Response(Boolean succeed) {
        this.succeed = succeed;
        this.data = null;
        this.message = succeed ? "成功" : "失败";
    }

    public Response(Boolean succeed, String message) {
        this.succeed = succeed;
        this.data = null;
        this.message = message;
    }


    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
