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
    private static final String CLIENT_ROOT_PATH = System.getProperty("user.dir") + File.separator + "client";
    private static final String SERVER_ROOT_PATH = System.getProperty("user.dir") + File.separator + "server";
    private static final String DOWNLOAD_ROOT_PATH = System.getProperty("user.dir") + File.separator + "download";


    /**
     * 将记录导出到本地文件
     *
     * @param list 列表
     */
    public static void export(List<Bill> list, String username) {
        makeDir(CLIENT_ROOT_PATH);
        //指定文件路径
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String filename = format.format(date) + "-" + username + "-" +
                UUID.randomUUID().toString().replaceAll("-", "") + ".txt";
        File file = new File(CLIENT_ROOT_PATH + File.separator + filename);
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
    public static void makeDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("该文件夹不存在，准备创建[" + path + "]");
            file.mkdirs();
        }
    }

    public static List<String> getAllFiles() {
        return getAllFile(CLIENT_ROOT_PATH, false);
    }

    /**
     * 列出我的文件列表
     *
     * @param username 用户名
     * @return {@code List<String>}
     */
    public static List<String> listMyFiles(String username) {
        return getAllFile(SERVER_ROOT_PATH + File.separator + username, false);
    }

    /**
     * 文件转换为字节数组
     *
     * @param path 路径
     * @return {@code byte[]}
     */
    public static byte[] fileConvertToByteArray(String path) {
        File file = new File(path);
        return fileConvertToByteArray(file);
    }

    /**
     * 字节数组转换为文件
     *
     * @param username 用户名
     * @param data     数据
     * @param fileName 文件名称
     */
    public static void ByteArrayConvertToFile(String username, byte[] data, String fileName) {
        makeDir(SERVER_ROOT_PATH + File.separator + username);
        //拼接字符串
        String filePath = SERVER_ROOT_PATH + File.separator + username + File.separator + fileName;
        ByteArrayConvertToFile(filePath, data);

    }

    public static void saveFile(byte[] bytes, String fileName) {
        makeDir(DOWNLOAD_ROOT_PATH);
        String filePath = DOWNLOAD_ROOT_PATH + File.separator + fileName;
        ByteArrayConvertToFile(filePath, bytes);
    }

    private static void ByteArrayConvertToFile(String filepath, byte[] data) {
        if (data != null) {
            File file = new File(filepath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data, 0, data.length);
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

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

    private static byte[] fileConvertToByteArray(File file) {
        byte[] data = null;
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
