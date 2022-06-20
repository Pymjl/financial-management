package cuit.pymjl.utils;

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
}
