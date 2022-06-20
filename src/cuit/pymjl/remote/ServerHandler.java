package cuit.pymjl.remote;

import cuit.pymjl.constant.Group;
import cuit.pymjl.entity.Bill;
import cuit.pymjl.entity.Request;
import cuit.pymjl.entity.Response;
import cuit.pymjl.factory.SingletonFactory;
import cuit.pymjl.service.BillService;
import cuit.pymjl.service.UserService;
import cuit.pymjl.service.impl.BillServiceImpl;
import cuit.pymjl.service.impl.UserServiceImpl;

import java.util.List;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:34
 **/
public class ServerHandler {
    private final UserService userService;
    private final BillService billService;

    public ServerHandler() {
        this.userService = SingletonFactory.getInstance(UserServiceImpl.class);
        this.billService = SingletonFactory.getInstance(BillServiceImpl.class);
    }

    public Response handle(Request request) {
        //获取请求参数
        Integer group = request.getGroup();
        Integer option = request.getOption();
        //构造返回参数
        Response response = new Response(false);
        if (group.equals(Group.Main_MENU.getGroup())) {
            switch (option) {
                case 1:
                    boolean login = userService.login(request);
                    if (login) {
                        response.setSucceed(true);
                        response.setMessage("登录成功");
                        break;
                    } else {
                        response.setMessage("用户名或密码错误，登录失败");
                    }
                    break;
                case 2:
                    //判断用户名是否存在
                    if (userService.isExist(request)) {
                        response.setMessage("该用户名已存在，请重新输入");
                        break;
                    }
                    //插入数据
                    userService.register(request);
                    response.setSucceed(true);
                    response.setMessage("注册成功");
                    break;
                default:
                    System.err.println("操作符异常");
            }
        } else if (group.equals(Group.SECONDARY_MENU.getGroup())) {
            switch (option) {
                //返回当前登录用户的所有账务信息
                case 1:
                    getAllBills(request, response);
                    break;
                //多条件查询
                case 2:
                    getAllBillsByTime(request, response);
                    break;
                default:
                    System.err.println("操作符异常");
            }
        }
        return response;
    }

    /**
     * 多条件查询
     *
     * @param request  请求
     * @param response 响应
     */
    private void getAllBillsByTime(Request request, Response response) {
        List<Bill> bills = billService.queryBillsByTime(request);
        if (bills == null || bills.size() == 0) {
            response.setMessage("您在此时间内还没有任何记账信息哟");
        } else {
            response.setSucceed(true);
            response.setData(bills);
        }
    }

    /**
     * 返回当前登录用户的所有账务信息
     *
     * @param request  请求
     * @param response 响应
     */
    private void getAllBills(Request request, Response response) {
        List<Bill> bills = billService.queryBillsByUsername(request);
        if (bills == null || bills.size() == 0) {
            response.setMessage("您还没有任何记账信息哟");
        } else {
            response.setSucceed(true);
            response.setData(bills);
        }
    }
}
