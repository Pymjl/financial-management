package cuit.pymjl.service.impl;

import cuit.pymjl.dao.UserDao;
import cuit.pymjl.dao.impl.UserDaoImpl;
import cuit.pymjl.entity.Bill;
import cuit.pymjl.entity.Request;
import cuit.pymjl.entity.User;
import cuit.pymjl.factory.SingletonFactory;
import cuit.pymjl.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 23:03
 **/
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public void register(Request request) {
        User user = (User) request.getData();
        userDao.saveUser(user);
    }

    @Override
    public boolean isExist(Request request) {
        User user = (User) request.getData();
        return userDao.isExist(user.getUsername());
    }

    @Override
    public boolean login(Request request) {
        User user = (User) request.getData();
        return userDao.selectUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
