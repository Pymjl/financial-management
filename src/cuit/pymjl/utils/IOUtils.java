package cuit.pymjl.utils;

import cuit.pymjl.entity.Bill;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 17:35
 **/
public class IOUtils {
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "client";

    static {
        makeDir();
    }

    /**
     * 将记录导出到本地文件
     *
     * @param list 列表
     */
    public static void export(List<Bill> list, String username) {
        //指定文件路径
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String filename = format.format(date) + "-" + username + "-" +
                UUID.randomUUID().toString().replaceAll("-", "") + ".txt";
        File file = new File(ROOT_PATH + File.separator + filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Writer w = null;
        //使用字符流，必须关闭程序才会执行，没有关闭前，内容都是存下内存区的缓冲区
        try {
            w = new FileWriter(file, true);
            w.write("========================欢迎来到记账系统=========================\r\n");
            w.write("id\t\t用途\t\t\t金额\t\t\t账户\t\t\t创建时间\t\t\t\t描述\r\n");
            for (Bill bill : list) {
                w.write(bill.toString() + "\r\n");
                w.flush();
            }
            //关闭
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建文件夹
     */
    public static void makeDir() {
        File file = new File(ROOT_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static List<String> getAllFiles() {
        return getAllFile(ROOT_PATH, false);
    }

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    private static List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

}
