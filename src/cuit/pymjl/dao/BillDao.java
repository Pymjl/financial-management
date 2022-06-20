package cuit.pymjl.dao;

import cuit.pymjl.entity.Bill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 12:01
 **/
public interface BillDao {
    /**
     * 通过用户名查询账单
     *
     * @param username 用户名
     * @return {@code List<Map<String, Object>>}
     */
    List<Map<String, Object>> queryBillsByUsername(String username);

    /**
     * 查询某用户一段时间的记账信息
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param username  用户名
     * @return {@code List<Map<String, Object>>}
     */
    List<Map<String, Object>> queryBillsByTime(Date startTime, Date endTime, String username);

    /**
     * 询某用户某天的记账信息
     *
     * @param startTime 开始时间
     * @param username  用户名
     * @return {@code List<Map<String, Object>>}
     */
    List<Map<String, Object>> queryBillsByTime(Date startTime, String username);

    /**
     * 添加账务
     *
     * @param bill 账务
     * @return boolean
     */
    boolean saveBill(Bill bill);

    /**
     * 模糊查询
     *
     * @param purposes 用途
     * @return {@code List<Map<String, Object>>}
     */
    List<Map<String, Object>> search(String purposes, String username);

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     */
    boolean delete(Integer id);

    /**
     * 更新
     *
     * @param bill 账务
     * @return boolean
     */
    boolean update(Bill bill);
}
