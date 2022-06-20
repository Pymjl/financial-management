package cuit.pymjl.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 23:04
 **/
public class GetHost {
    public static void main(String[] args) throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostAddress();
        System.out.println(host);
    }
}
