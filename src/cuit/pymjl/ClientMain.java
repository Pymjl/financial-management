package cuit.pymjl;

import cuit.pymjl.constant.Group;
import cuit.pymjl.entity.Response;
import cuit.pymjl.entity.User;
import cuit.pymjl.remote.ClientHandler;
import cuit.pymjl.utils.MenuUtils;
import cuit.pymjl.utils.StringUtils;

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
            Response response = null;
            switch (opt) {
                case 1:
                    User userInfo = inputUserInfo(scanner);
                    response = handler.invoke(1, Group.Main_MENU.getGroup(), userInfo);
                    System.out.println(response.getMessage());
                    Thread.sleep(1000);
                    if (response.getSucceed()) {
                        jump(scanner);
                    }
                    break;
                case 2:
                    User user = getUser(scanner);
                    response = handler.invoke(2, Group.Main_MENU.getGroup(), user);
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
     * 处理二级菜单的逻辑
     */
    private static void jump(Scanner scanner) {
        while (true) {
            MenuUtils.printSecondaryMenu();
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt);
            //退出标记
            int flag = 0;

            switch (option) {
                case 0:
                    flag = 1;
                    break;
                case 1:
                    System.out.println("1.查询账务");
                    break;
                case 2:
                    System.out.println("2.多条件查询");
                    break;
                case 3:
                    System.out.println("3.添加账务");
                    break;
                case 4:
                    System.out.println("4.编辑账务");
                    break;
                case 5:
                    System.out.println("5.删除账务");
                    break;
                case 6:
                    System.out.println("6.搜索账务");
                    break;
                case 7:
                    System.out.println("7.上传账务");
                    break;
                case 8:
                    System.out.println("8.下载账务");
                    break;
                default:
                    System.out.println("操作符异常，请重新输入");
            }
            if (flag == 1) {
                break;
            }
        }
    }

    private static User inputUserInfo(Scanner scanner) {
        System.out.println("请输入你的用户名:");
        String username = scanner.nextLine();
        while (StringUtils.isBlank(username)) {
            System.out.println("username不能为空请重新输入");
            username = scanner.nextLine();
        }

        System.out.println("请输入你的密码:");
        String password = scanner.nextLine();
        while (StringUtils.isBlank(password) || password.length() < 6) {
            System.out.println("密码至少是长度为6的字符串，请重新输入");
            password = scanner.nextLine();
        }
        return new User(username, password);
    }

    /**
     * 获取用户
     *
     * @param scanner 扫描仪
     * @return {@code User}
     */
    private static User getUser(Scanner scanner) {
        System.out.println("请输入你的用户名:");
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
