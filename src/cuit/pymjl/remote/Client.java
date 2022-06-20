package cuit.pymjl.remote;

import config.Transport;
import cuit.pymjl.entity.Request;
import cuit.pymjl.utils.SerializeUtil;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 21:35
 **/
public class Client extends Socket {

    public Object sendRequest(Request request) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(Transport.SERVER_HOST, Transport.SERVER_PORT));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 通过输出流向服务器发送数据
            objectOutputStream.writeObject(request);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("服务端调用失败");
        }
    }
}
