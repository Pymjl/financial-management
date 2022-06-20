package cuit.pymjl.utils;

import cuit.pymjl.entity.Bill;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 17:35
 **/
public class IOUtils {
    private static final String ROOT_PATH = System.getProperty("user.dir");

    /**
     * 将记录导出到本地文件
     *
     * @param list 列表
     */
    public static void export(List<Bill> list) {
        //创建文件
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String filename = format.format(date) + ".txt";
        File file = new File(ROOT_PATH + "/client/" + filename);
        if (!file.exists()) {
            try {
                System.out.println("文件不存在，创建文件，文件路径为==>[" + file.getAbsolutePath() + "]");
                boolean newFile = file.createNewFile();
                System.out.println("创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //开始写数据
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(list);
            out.close();
            System.out.println("导出成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
