package cuit.pymjl.remote;

import cuit.pymjl.constant.Group;
import cuit.pymjl.entity.Request;
import cuit.pymjl.entity.Response;
import cuit.pymjl.factory.SingletonFactory;
import cuit.pymjl.service.UserService;
import cuit.pymjl.service.impl.UserServiceImpl;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:34
 **/
public class ServerHandler {
    private final UserService userService;

    public ServerHandler() {
        this.userService = SingletonFactory.getInstance(UserServiceImpl.class);
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
        }
        return response;
    }
}
