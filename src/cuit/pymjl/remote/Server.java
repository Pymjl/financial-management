package cuit.pymjl.remote;

import cuit.pymjl.constant.Transport;
import cuit.pymjl.entity.Request;
import cuit.pymjl.entity.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 21:42
 **/
public class Server {

    public void start() {
        Socket socket = null;
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            //绑定服务端地址
            String host = InetAddress.getLocalHost().getHostAddress();
            server.bind(new InetSocketAddress(host, Transport.SERVER_PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务端启动成功");
        try {
            while ((socket = server.accept()) != null) {
                System.out.println("client connected [" + socket.getInetAddress() + "]");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) objectInputStream.readObject();
                //进入代理类调用方法
                ServerHandler serverHandler = new ServerHandler();
                Object response = serverHandler.handle(request);
                //将结果写回客户端
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Response<String> response = new Response<>(false);
            //将结果写回客户端
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(response);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }
}
