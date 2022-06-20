package cuit.pymjl.dao.impl;

import cuit.pymjl.dao.BillDao;
import cuit.pymjl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 12:01
 **/
public class BillDaoImpl implements BillDao {
    @Override
    public List<Map<String, Object>> queryBillsByUsername(String username) {
        System.out.println("开始根据username=[" + username + "]查询该用户的所有记账......");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from airui_zhangwu where username=?";
        try {
            return queryRunner.query(sql, new MapListHandler(), username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
