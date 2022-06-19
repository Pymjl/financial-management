package cuit.pymjl.test;

import cuit.pymjl.dao.UserDao;
import cuit.pymjl.dao.impl.UserDaoImpl;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 0:47
 **/
public class MainTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.isExist("Pymjl"));
    }
}
