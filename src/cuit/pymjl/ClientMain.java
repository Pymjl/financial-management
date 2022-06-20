package cuit.pymjl;

import cuit.pymjl.constant.Group;
import cuit.pymjl.entity.*;
import cuit.pymjl.remote.ClientHandler;
import cuit.pymjl.utils.IOUtils;
import cuit.pymjl.utils.MenuUtils;
import cuit.pymjl.utils.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:09
 **/
public class ClientMain {
    private static final ClientHandler handler = new ClientHandler();

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        Scanner scanner = new Scanner(System.in);
        //用于存放登录后的用户名
        String username = null;
        while (true) {
            MenuUtils.printMainMenu();
            //这里需要注意，输入整型后再输入字符串，字符串会为空，所以这里需要特殊处理
            String option = scanner.nextLine();
            int opt = Integer.parseInt(option);
            Response response = null;
            switch (opt) {
                //登录
                case 1:
                    User userInfo = inputUserInfo(scanner);
                    //调用handler向服务端发送请求，并接收响应
                    response = handler.invoke(1, Group.Main_MENU.getGroup(), userInfo);
                    //输出返回的响应
                    System.out.println(response.getMessage());
                    Thread.sleep(1000);
                    //登陆成功后跳转到二级菜单
                    if (response.getSucceed()) {
                        username = userInfo.getUsername();
                        jump(scanner, username);
                    }
                    break;
                //注册
                case 2:
                    User user = getUser(scanner);
                    //调用handler向服务端发送请求，并接收响应
                    response = handler.invoke(2, Group.Main_MENU.getGroup(), user);
                    System.out.println(response.getMessage());
                    Thread.sleep(1000);
                    break;
                //退出
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
    private static void jump(Scanner scanner, String username) throws InterruptedException, ParseException {
        while (true) {
            MenuUtils.printSecondaryMenu();
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt);
            Response response = null;
            //退出标记
            int flag = 0;

            switch (option) {
                //返回上一级菜单
                case 0:
                    flag = 1;
                    break;
                //返回当前登录用户的所有账务信息
                case 1:
                    response = handler.invoke(1, Group.SECONDARY_MENU.getGroup(), username);
                    MenuUtils.printRecords(response, username);
                    Thread.sleep(1000);
                    break;
                //多条件查询
                case 2:
                    Condition condition = getCondition(username, scanner);
                    response = handler.invoke(2, Group.SECONDARY_MENU.getGroup(), condition);
                    MenuUtils.printRecords(response, username);
                    Thread.sleep(1000);
                    break;
                //添加账务
                case 3:
                    Bill bill = inputBillInfo(username, scanner);
                    response = handler.invoke(3, Group.SECONDARY_MENU.getGroup(), bill);
                    System.out.println(response.getMessage());
                    Thread.sleep(1000);
                    break;
                //4.编辑账务
                case 4:
                    //先查询该用户的所有账务
                    response = handler.invoke(1, Group.SECONDARY_MENU.getGroup(), username);
                    MenuUtils.printRecords(response, username);
                    if (!response.getSucceed()) {
                        System.out.println("您还没有账务记录可以编辑");
                        break;
                    }
                    //暂存查询的结果集
                    List<Bill> cache = (List<Bill>) response.getData();
                    Bill newRecord = selector(scanner, cache);
                    response = handler.invoke(4, Group.SECONDARY_MENU.getGroup(), newRecord);
                    System.out.println(response.getMessage());
                    Thread.sleep(1000);
                    break;
                //删除账务
                case 5:
                    //先查询该用户的所有账务
                    response = handler.invoke(1, Group.SECONDARY_MENU.getGroup(), username);
                    MenuUtils.printRecords(response, username);
                    if (!response.getSucceed()) {
                        System.out.println("您还没有账务记录可以删除");
                        break;
                    }
                    System.out.println("请输入ID删除对应的账单:");
                    String billId = scanner.nextLine();
                    if (!StringUtils.isDigit(billId)) {
                        System.out.println("输入的数据有误，请重新操作");
                    } else {
                        int id = Integer.parseInt(billId);
                        response = handler.invoke(5, Group.SECONDARY_MENU.getGroup(), id);
                        System.out.println(response.getMessage());
                    }
                    break;
                //6.搜索账务
                case 6:
                    System.out.println("请输入关键词：");
                    String key = scanner.nextLine();
                    if (StringUtils.isNull(key)) {
                        System.out.println("关键词不能为null");
                    } else {
                        Key keywords = new Key(username, key);
                        response = handler.invoke(6, Group.SECONDARY_MENU.getGroup(), keywords);
                        MenuUtils.printRecords(response, username);
                        Thread.sleep(1000);
                    }
                    break;
                //上传文件
                case 7:
                    String path = getFilePath(scanner);
                    if (path == null) {
                        break;
                    }
                    response = handler.upload(7, Group.SECONDARY_MENU.getGroup(), path, username);
                    System.out.println(response.getMessage());
                    Thread.sleep(1000);
                    break;
                //8.下载账务
                case 8:
                    //先查看当前登录用户的拥有的文件,9在服务端表示查询文件
                    response = handler.invoke(9, Group.SECONDARY_MENU.getGroup(), username);
                    String targetFilePath = MenuUtils.printFileRecords(response);
                    response = handler.download(8, Group.SECONDARY_MENU.getGroup(), targetFilePath);
                    //将文件保存到本地
                    MyFile srcFile = (MyFile) response.getData();
                    IOUtils.saveFile(srcFile.getBytes(), srcFile.getFileName());
                    System.out.println(response.getMessage());
                    Thread.sleep(1000);
                    break;
                default:
                    System.out.println("操作符异常，请重新输入");
            }
            //退出标记，如果为1，就返回上一级菜单
            if (flag == 1) {
                break;
            }
        }
    }

    private static String getFilePath(Scanner scanner) {
        //先获取本地的所有文件
        List<String> files = IOUtils.getAllFiles();
        if (files.isEmpty()) {
            System.out.println("本地还没有文件，请先导出再上传");
            return null;
        }
        System.out.println("============================本地的文件有============================");
        for (int i = 0; i < files.size(); i++) {
            System.out.println(i + 1 + "." + files.get(i));
        }
        System.out.println("请输入你要上传哪一个文件：");
        String opt = scanner.nextLine();
        if (!StringUtils.isDigit(opt)) {
            System.out.println("操作符有误，请重新操作");
            return null;
        }
        int option = Integer.parseInt(opt);
        if (option < 1 || option > files.size()) {
            System.out.println("操作符越界，请重新输入");
            return null;
        }
        return files.get(option - 1);
    }

    private static Bill selector(Scanner scanner, List<Bill> cache) {
        while (true) {
            System.out.println("请输入你要编辑的账务ID:");
            String billId = scanner.nextLine();
            if (!StringUtils.isDigit(billId)) {
                System.out.println("输入的格式有误，请重新输入");
                continue;
            }
            int id = Integer.parseInt(billId);
            Bill bill = getBill(id, cache);
            if (bill == null) {
                System.out.println("你输入的id不存在，请重新输入");
                continue;
            }

            System.out.println("请选择操作：1.更改该记录的某一个属性 2.更改整个记录");
            String opt = scanner.nextLine();
            if (!StringUtils.isDigit(opt)) {
                System.out.println("输入的格式有误，请重新输入");
                continue;
            }
            int option = Integer.parseInt(opt);

            if (option == 1) {
                System.out.println("请选择更改哪一个属性: 1.用途 2.金额 3.账户 4.描述");
                String ch = scanner.nextLine();
                if (!StringUtils.isDigit(ch)) {
                    System.out.println("输入的格式有误，请重新输入");
                    continue;
                }
                int choice = Integer.parseInt(ch);
                if (choice < 1 || choice > 4) {
                    System.out.println("输入的操作符有误，请重新操作");
                    continue;
                }

                System.out.println("请输入更改后的值:");
                String data = scanner.nextLine();
                switch (choice) {
                    case 1:
                        bill.setPurposes(data);
                        return bill;
                    case 2:
                        bill.setMoney(Double.parseDouble(data));
                        return bill;
                    case 3:
                        bill.setAccount(data);
                        return bill;
                    case 4:
                        bill.setDescription(data);
                        return bill;
                    default:
                        System.out.println("操作符异常，请重新输入");
                }
            } else if (option == 2) {
                Bill newBill = inputBillInfo(bill.getUsername(), scanner);
                newBill.setId(bill.getId());
                return newBill;
            } else {
                System.out.println("操作符异常，请重新输入");
            }
        }
    }

    private static Bill getBill(int id, List<Bill> cache) {
        for (Bill bill : cache) {
            if (bill.getId().equals(id)) {
                return bill;
            }
        }
        return null;
    }

    private static Bill inputBillInfo(String username, Scanner scanner) {
        Bill bill = new Bill();
        while (true) {
            System.out.println("请输入该账单的用途：");
            String pro = scanner.nextLine();
            System.out.println("请输入该账单的金额：");
            String money = scanner.nextLine();
            System.out.println("请输入该账单的账户所属银行：");
            String account = scanner.nextLine();
            System.out.println("请输入该账单的描述：");
            String description = scanner.nextLine();
            boolean isLegal = StringUtils.isBlank(pro, money, account, description);
            if (isLegal) {
                System.out.println("参数异常，所有参数不能为空，请重新输入");
            } else {
                bill.setAccount(account);
                bill.setUsername(username);
                bill.setCreateTime(new Date());
                bill.setDescription(description);
                Double mon = Double.parseDouble(money);
                bill.setMoney(mon);
                bill.setPurposes(pro);
                break;
            }
        }
        return bill;
    }

    /**
     * 得到条件
     *
     * @param username 用户名
     * @param scanner  扫描仪
     * @return {@code Condition}
     */
    private static Condition getCondition(String username, Scanner scanner) throws ParseException {
        Condition condition = new Condition();
        condition.setUsername(username);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("请选择查询条件：1.查询某一天的账务 2.查询某一段时间的账务");
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt);
            if (option == 1) {
                System.out.println("请您按照格式输入要查询的时间(eg:2022-10-27)：");
                String start = scanner.nextLine();
                boolean check = check(start);
                if (check) {
                    condition.setStartTime(format.parse(start));
                    break;
                } else {
                    System.out.println("您的输入不符合格式，请重新输入");
                }
            } else if (option == 2) {
                System.out.println("请您按照格式输入要查询的时间段:");
                System.out.println("请您按照格式输入要查询的时间段的开始时间(eg:2022-10-28)：");
                String start = scanner.nextLine();
                System.out.println("请您按照格式输入要查询的时间段的结束时间(eg:2022-10-29)：");
                String end = scanner.nextLine();
                boolean isLegal = check(start) && check(end);
                if (isLegal) {
                    condition.setStartTime(format.parse(start));
                    condition.setEndTime(format.parse(end));
                    break;
                } else {
                    System.out.println("您的输入不符合格式，请重新输入");
                }
            } else {
                System.out.println("操作符异常，请重新输入");
            }
        }
        return condition;
    }

    /**
     * 检查字符串是否符合时间格式
     *
     * @param str str
     * @return boolean
     */
    private static boolean check(String str) {
        String[] split = str.split("-");
        if (split.length != 3) {
            return false;
        }
        for (String s : split) {
            boolean isDigit = StringUtils.isDigit(s);
            if (!isDigit) {
                return false;
            }
        }
        return true;
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
