package cuit.pymjl.entity;

import java.util.Date;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 11:48
 **/
public class Bill {
    /**
     * id
     */
    private Integer id;
    /**
     * 用途
     */
    private String purposes;
    /**
     * 金额
     */
    private Double money;
    /**
     * 账户
     */
    private String account;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 描述
     */
    private String description;
    /**
     * 用户名
     */
    private String username;

    public Bill() {
    }

    public Bill(Integer id, String purposes, Double money, String account, Date createTime, String description, String username) {
        this.id = id;
        this.purposes = purposes;
        this.money = money;
        this.account = account;
        this.createTime = createTime;
        this.description = description;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurposes() {
        return purposes;
    }

    public void setPurposes(String purposes) {
        this.purposes = purposes;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", purposes='" + purposes + '\'' +
                ", money=" + money +
                ", account='" + account + '\'' +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
