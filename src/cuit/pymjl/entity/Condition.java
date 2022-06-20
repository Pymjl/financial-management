package cuit.pymjl.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 12:22
 **/
public class Condition implements Serializable {
    @Serial
    private static final long serialVersionUID = -5801112578272943999L;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 用户名
     */
    private String username;

    public Condition(Date startTime, Date endTime, String username) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.username = username;
    }

    public Condition(Date startTime, String username) {
        this.startTime = startTime;
        this.endTime = null;
        this.username = username;
    }

    public Condition() {
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
