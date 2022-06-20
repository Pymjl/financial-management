package cuit.pymjl.test;

import cuit.pymjl.dao.BillDao;
import cuit.pymjl.dao.UserDao;
import cuit.pymjl.dao.impl.BillDaoImpl;
import cuit.pymjl.dao.impl.UserDaoImpl;
import cuit.pymjl.entity.Request;
import cuit.pymjl.service.BillService;
import cuit.pymjl.service.impl.BillServiceImpl;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 0:47
 **/
public class MainTest {
    public static void main(String[] args) {
        Request request = new Request();
        request.setData("qiweikai");
        BillService billService = new BillServiceImpl();
        billService.queryBillsByUsername(request).forEach(System.out::println);
    }
}
