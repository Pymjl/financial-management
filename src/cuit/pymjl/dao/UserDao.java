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
}
