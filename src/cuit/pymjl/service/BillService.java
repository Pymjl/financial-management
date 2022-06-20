package cuit.pymjl.service;

import cuit.pymjl.entity.Bill;
import cuit.pymjl.entity.Request;

import java.util.List;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 12:01
 **/
public interface BillService {
    /**
     * 通过用户名查询账单
     *
     * @param request 请求
     * @return {@code List<Bill>}
     */
    List<Bill> queryBillsByUsername(Request request);
}
