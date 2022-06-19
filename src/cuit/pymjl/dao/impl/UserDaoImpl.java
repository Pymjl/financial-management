package cuit.pymjl.dao.impl;

import cuit.pymjl.dao.UserDao;
import cuit.pymjl.entity.User;
import cuit.pymjl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:45
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public void saveUser(User user) {
        System.out.println("开始向数据库插入数据......");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "INSERT INTO user(username,password) VALUES(?,?)";
        try {
            queryRunner.update(sql, user.getUsername(), user.getPassword());
            System.out.println("插入用户成功......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean isExist(String username) {
        System.out.println("开始判断数据库是否存在该用户名[" + username + "]......");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from user where binary username=?";
        try {
            Map<String, Object> map = queryRunner.query(sql, new MapHandler(), username);
            if (map == null || map.size() == 0) {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean selectUserByUsernameAndPassword(String username, String password) {
        System.out.println("开始根据username=[" + username + "], password=[" + password + "]查询用户");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from user where binary username=? and password=?";
        try {
            Map<String, Object> data = queryRunner.query(sql, new MapHandler(), username, password);
            if (data == null || data.size() == 0) {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
