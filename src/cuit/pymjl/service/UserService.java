package cuit.pymjl.service;

import cuit.pymjl.entity.Bill;
import cuit.pymjl.entity.Request;
import cuit.pymjl.entity.User;

import java.util.List;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 23:03
 **/
public interface UserService {

    /**
     * 注册
     *
     * @param request 请求
     */
    void register(Request request);

    /**
     * 用户名是否存在
     *
     * @param request 请求
     * @return boolean
     */
    boolean isExist(Request request);

    /**
     * 登录
     *
     * @param request 请求
     * @return boolean
     */
    boolean login(Request request);
}
