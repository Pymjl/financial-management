package cuit.pymjl.dao;

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
}
