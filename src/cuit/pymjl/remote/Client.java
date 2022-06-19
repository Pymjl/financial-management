package cuit.pymjl.remote;

import cuit.pymjl.constant.Transport;
import cuit.pymjl.entity.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 21:35
 **/
public class Client {

    public Object sendRequest(Request request) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(Transport.SERVER_HOST, Transport.SERVER_PORT));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // 通过输出流向服务器发送数据
            objectOutputStream.writeObject(request);
            //TODO 服务端的响应
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("服务端调用失败");
        }
    }


}
