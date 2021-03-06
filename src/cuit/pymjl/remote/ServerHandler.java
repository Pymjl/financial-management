package cuit.pymjl.remote;

import cuit.pymjl.constant.Group;
import cuit.pymjl.entity.Bill;
import cuit.pymjl.entity.MyFile;
import cuit.pymjl.entity.Request;
import cuit.pymjl.entity.Response;
import cuit.pymjl.factory.SingletonFactory;
import cuit.pymjl.service.BillService;
import cuit.pymjl.service.UserService;
import cuit.pymjl.service.impl.BillServiceImpl;
import cuit.pymjl.service.impl.UserServiceImpl;
import cuit.pymjl.utils.IOUtils;

import java.io.File;
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
                //添加账务
                case 3:
                    boolean succeed = billService.saveBill(request);
                    if (succeed) {
                        response.setSucceed(true);
                        response.setMessage("添加成功");
                    }
                    break;
                //编辑
                case 4:
                    boolean update = billService.update(request);
                    if (update) {
                        response.setMessage("更新成功");
                        response.setSucceed(true);
                    }
                    break;
                //删除
                case 5:
                    boolean res = billService.delete(request);
                    if (res) {
                        response.setSucceed(true);
                        response.setMessage("删除成功");
                    }
                    break;
                //模糊查询
                case 6:
                    search(request, response);
                    break;
                //上传文件
                case 7:
                    upload(request, response);
                    break;
                //下载文件
                case 8:
                    download(request, response);
                    break;
                //查询用户的文件
                case 9:
                    findAllFiles(request, response);
                    break;
                default:
                    System.err.println("操作符异常");
            }
        }
        return response;
    }

    /**
     * 下载
     *
     * @param request  请求
     * @param response 响应
     */
    private void download(Request request, Response response) {
        String targetPath = (String) request.getData();
        byte[] bytes = IOUtils.fileConvertToByteArray(targetPath);
        MyFile myFile = new MyFile();
        myFile.setBytes(bytes);
        myFile.setFileName(targetPath.substring(targetPath.lastIndexOf(File.separator) + 1));
        response.setData(myFile);
        response.setSucceed(true);
        response.setMessage("下载成功");
    }

    /**
     * 查找所有文件
     *
     * @param request  请求
     * @param response 响应
     */
    private void findAllFiles(Request request, Response response) {
        String username = (String) request.getData();
        List<String> list = IOUtils.listMyFiles(username);
        if (list.isEmpty()) {
            response.setMessage("你还没有上传文件哟");
        } else {
            response.setSucceed(true);
            response.setData(list);
        }
    }

    /**
     * 上传文件
     *
     * @param request  请求
     * @param response 响应
     */
    private void upload(Request request, Response response) {
        MyFile myFile = (MyFile) request.getData();
        IOUtils.ByteArrayConvertToFile(myFile.getUsername(), myFile.getBytes(), myFile.getFileName());
        response.setSucceed(true);
        response.setMessage("上传成功");
    }

    /**
     * 搜索
     *
     * @param request  请求
     * @param response 响应
     */
    private void search(Request request, Response response) {
        List<Bill> res = billService.search(request);
        if (res == null || res.size() == 0) {
            response.setMessage("无相关记录");
        } else {
            response.setSucceed(true);
            response.setData(res);
        }
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
