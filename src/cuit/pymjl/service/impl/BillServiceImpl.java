package cuit.pymjl.service.impl;

import cuit.pymjl.dao.BillDao;
import cuit.pymjl.dao.impl.BillDaoImpl;
import cuit.pymjl.entity.Bill;
import cuit.pymjl.entity.Request;
import cuit.pymjl.service.BillService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 12:01
 **/
public class BillServiceImpl implements BillService {
    private final BillDao billDao;

    public BillServiceImpl() {
        this.billDao = new BillDaoImpl();
    }

    @Override
    public List<Bill> queryBillsByUsername(Request request) {
        String username = (String) request.getData();
        List<Map<String, Object>> list = billDao.queryBillsByUsername(username);
        return mapListToBillList(list);
    }

    private List<Bill> mapListToBillList(List<Map<String, Object>> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<Bill> res = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Integer id = (Integer) map.get("zwid");
            String purposes = (String) map.get("flname");
            Double money = (Double) map.get("money");
            String account = (String) map.get("zhanghu");
            Date createTime = (Date) map.get("createtime");
            String description = (String) map.get("description");
            String username = (String) map.get("username");
            Bill bill = new Bill(id, purposes, money, account, createTime, description, username);
            res.add(bill);
        }
        return res;
    }
}
