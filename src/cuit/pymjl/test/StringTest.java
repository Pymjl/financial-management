package cuit.pymjl.test;

import cuit.pymjl.utils.StringUtils;

import java.io.File;
import java.util.Scanner;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 14:22
 **/
public class StringTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String path = "C:\\Users\\Admin\\JavaProjects\\financial-management\\test.txt";
        String filename = path.substring(path.lastIndexOf(File.separator) + 1);
        System.out.println(filename);
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);

        String s = "2022-10-28";
        String[] split = s.split("-");
        for (String ss : split) {
            System.out.println(StringUtils.isDigit(ss));
        }
        System.out.println(StringUtils.isDigit("we231"));
    }
}
