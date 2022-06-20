package cuit.pymjl.test;

import cuit.pymjl.entity.Request;
import cuit.pymjl.utils.SerializeUtil;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 19:50
 **/
public class SerializeUtilTest {
    public static void main(String[] args) {
        Request request = new Request(1, "wewewe", 1);
        byte[] bytes = SerializeUtil.serializeObj(request);
        System.out.println(bytes);
        System.out.println(SerializeUtil.unserializeObj(bytes));
    }
}
