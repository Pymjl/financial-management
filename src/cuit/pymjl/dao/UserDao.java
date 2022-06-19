package cuit.pymjl.dao;

import cuit.pymjl.entity.User;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:46
 **/
public interface UserDao {
    /**
     * 保存用户
     *
     * @param user 用户
     */
    void saveUser(User user);

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    boolean isExist(String username);

    /**
     * 通过用户名和密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return boolean
     */
    boolean selectUserByUsernameAndPassword(String username, String password);
}
