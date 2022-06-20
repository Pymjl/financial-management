package cuit.pymjl.test;

import cuit.pymjl.entity.Condition;
import cuit.pymjl.entity.Request;
import cuit.pymjl.service.BillService;
import cuit.pymjl.service.impl.BillServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 0:47
 **/
public class BillServiceTest {
    private static final BillService billService = new BillServiceImpl();

    public static void main(String[] args) {
        testQueryBillsByTime2();
    }

    private static void testQueryBillsByUsername() {
        Request request = new Request();
        request.setData("qiweikai");
        billService.queryBillsByUsername(request).forEach(System.out::println);
    }

    private static void testQueryBillsByTime1() {
        String startTime = "2016-10-28";
        String endTime = "2016-10-29";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            Condition condition = new Condition(start, end, "qiweikai");
            Request request = new Request();
            request.setData(condition);
            billService.queryBillsByTime(request).forEach(System.out::println);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void testQueryBillsByTime2() {
        String startTime = "2016-10-28";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = format.parse(startTime);
            Condition condition = new Condition(start, "qiweikai");
            Request request = new Request();
            request.setData(condition);
            billService.queryBillsByTime(request).forEach(System.out::println);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
