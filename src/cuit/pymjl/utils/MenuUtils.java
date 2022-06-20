package cuit.pymjl.utils;

import cuit.pymjl.entity.Bill;
import cuit.pymjl.entity.Response;

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
    public static void printRecords(Response response) {
        if (response.getSucceed()) {
            System.out.println("========================欢迎来到记账系统=========================");
            System.out.println("id\t\t用途\t\t\t金额\t\t\t账户\t\t\t创建时间\t\t\t\t描述");
            List<Bill> data = (List<Bill>) response.getData();
            data.forEach(System.out::println);
            System.out.println("是否选择将其导出为文件保存到本地(yes or no)？");
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            if ("yes".equals(opt)) {
                IOUtils.export(data);
            }
        } else {
            System.out.println(response.getMessage());
        }
    }
}
