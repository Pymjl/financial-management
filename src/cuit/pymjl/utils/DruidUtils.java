package cuit.pymjl.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 20:40
 **/
public class DruidUtils {
    private static DruidDataSource dataSource;

    static {
        Properties prop = new Properties();
        try {
            prop.load(DruidUtils.class.getClassLoader().getResourceAsStream("config/druid.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
