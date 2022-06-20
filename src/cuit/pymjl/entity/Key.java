package cuit.pymjl.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 16:21
 **/
public class Key implements Serializable {
    @Serial
    private static final long serialVersionUID = -5801113418272943999L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 关键
     */
    private String key;

    public Key() {
    }

    public Key(String username, String key) {
        this.username = username;
        this.key = key;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Key{" +
                "username='" + username + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
