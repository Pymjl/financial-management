package cuit.pymjl.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 20:46
 **/
public class MyFile implements Serializable {
    @Serial
    private static final long serialVersionUID = -1231113418272943999L;
    private String fileName;
    private byte[] bytes;
    private String username;

    public MyFile(String fileName, byte[] bytes, String username) {
        this.fileName = fileName;
        this.bytes = bytes;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MyFile() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
