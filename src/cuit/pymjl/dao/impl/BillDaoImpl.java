package cuit.pymjl.dao.impl;

import cuit.pymjl.dao.BillDao;
import cuit.pymjl.entity.Bill;
import cuit.pymjl.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.Date;
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

    @Override
    public List<Map<String, Object>> queryBillsByTime(Date startTime, Date endTime, String username) {
        System.out.println("开始查询用户[" + username + "]从[" + startTime + "]到[" + endTime + "]间的所有记账.....");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT * FROM airui_zhangwu WHERE (createtime BETWEEN ? AND ?) AND username=?";
        try {
            return queryRunner.query(sql, new MapListHandler(), startTime, endTime, username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBillsByTime(Date startTime, String username) {
        System.out.println("开始查询用户[" + username + "]这一天[" + startTime + "]的所有记账.....");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT * FROM airui_zhangwu WHERE createtime= ? AND username=?";
        try {
            return queryRunner.query(sql, new MapListHandler(), startTime, username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveBill(Bill bill) {
        System.out.println("开始保存记账信息......");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "INSERT INTO airui_zhangwu(flname,money,zhanghu,createtime,description,username) VALUES(?,?,?,?,?,?)";
        Object[] params = {bill.getPurposes(), bill.getMoney(), bill.getAccount(),
                bill.getCreateTime(), bill.getDescription(), bill.getUsername()};
        try {
            queryRunner.update(sql, params);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> search(String purposes) {
        System.out.println("开始根据[" + purposes + "]模糊查询......");
        String pro = "%" + purposes + "%";
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT * FROM airui_zhangwu WHERE flname LIKE ?";
        try {
            return queryRunner.query(sql, new MapListHandler(), pro);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println("开始根据[" + id + "]删除......");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "DELETE FROM airui_zhangwu WHERE zwid=?";
        try {
            queryRunner.update(sql, id);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
