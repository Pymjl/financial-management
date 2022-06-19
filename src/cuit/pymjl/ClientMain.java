package cuit.pymjl;

import cuit.pymjl.constant.Group;
import cuit.pymjl.entity.Response;
import cuit.pymjl.entity.User;
import cuit.pymjl.remote.Client;
import cuit.pymjl.remote.ClientHandler;
import cuit.pymjl.utils.MenuUtils;
import cuit.pymjl.utils.StringUtils;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:09
 **/
public class ClientMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClientHandler handler = new ClientHandler();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            MenuUtils.printMainMenu();
            //这里需要注意，输入整型后再输入字符串，字符串会为空，所以这里需要特殊处理
            String option = scanner.nextLine();
            int opt = Integer.parseInt(option);
            switch (opt) {
                case 1:
                    System.out.println("登录");
                    break;
                case 2:
                    User user = getUser(scanner);
                    Response response = handler.invoke(2, Group.Main_MENU.getGroup(), user);
                    System.out.println(response.getMessage());
                    Thread.sleep(1000);
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("操作符异常，请重新输入");
            }
        }
    }

    /**
     * 获取用户
     *
     * @param scanner 扫描仪
     * @return {@code User}
     */
    private static User getUser(Scanner scanner) {
        System.out.println("请输入你的名字:");
        String username = scanner.nextLine();
        while (StringUtils.isBlank(username)) {
            System.out.println("username不能为空请重新输入");
            username = scanner.nextLine();
        }

        String password = null;
        while (true) {
            System.out.println("请输入你的密码:");
            password = scanner.nextLine();
            if (StringUtils.isBlank(password) || password.length() < 6) {
                System.out.println("密码至少是长度为6的字符串，请重新输入");
                continue;
            }
            System.out.println("请再次输入，确认你的密码：");
            String repeatedPassword = scanner.nextLine();
            if (!password.equals(repeatedPassword)) {
                System.out.println("两次密码不一致，请重新输入");
            } else {
                break;
            }
        }
        return new User(username, password);
    }

}
