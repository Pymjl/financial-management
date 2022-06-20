package cuit.pymjl.utils;

import cuit.pymjl.entity.Bill;
import cuit.pymjl.entity.Response;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 23:59
 **/
public class MenuUtils {
    /**
     * 打印主菜单
     */
    public static void printMainMenu() {
        System.out.println("================欢迎来到记账系统,请登录或者注册================");
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("0.退出");
    }

    /**
     * 打印二级菜单
     */
    public static void printSecondaryMenu() {
        System.out.println("================欢迎来到记账系统,请选择操作================");
        System.out.println("1.查询账务");
        System.out.println("2.多条件查询");
        System.out.println("3.添加账务");
        System.out.println("4.编辑账务");
        System.out.println("5.删除账务");
        System.out.println("6.搜索账务");
        System.out.println("7.上传账务");
        System.out.println("8.下载账务");
        System.out.println("0.返回主菜单");
    }

    /**
     * 打印记录
     *
     * @param response 响应
     */
    public static void printRecords(Response response, String username) {
        if (response.getSucceed()) {
            System.out.println("========================欢迎来到记账系统=========================");
            System.out.println("id\t\t用途\t\t\t金额\t\t\t账户\t\t\t创建时间\t\t\t\t描述");
            List<Bill> data = (List<Bill>) response.getData();
            data.forEach(System.out::println);
            System.out.println("是否选择将其导出为文件保存到本地(yes or no)？");
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            if ("yes".equals(opt)) {
                IOUtils.export(data, username);
                System.out.println("导出成功");
            }
            System.out.println("正在返回上一级菜单.....");
        } else {
            System.out.println(response.getMessage());
        }
    }

    public static String printFileRecords(Response response) {
        if (response.getSucceed()) {
            System.out.println("您保存在服务器上的文件有：");
            List<String> data = (List<String>) response.getData();
            for (int i = 0; i < data.size(); i++) {
                System.out.println(i + 1 + "." + data.get(i).substring(data.get(i).lastIndexOf(File.separator) + 1));
            }
            System.out.println("请输入你要下载的文件序号：");
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            if (!StringUtils.isDigit(opt)) {
                System.out.println("操作符有误，请重新输入");
                return null;
            }
            int option = Integer.parseInt(opt);
            if (option < 1 || option > data.size()) {
                System.out.println("操作符越界，请重新输入");
                return null;
            }
            return data.get(option - 1);
        } else {
            System.out.println(response.getMessage());
            return null;
        }
    }
}
