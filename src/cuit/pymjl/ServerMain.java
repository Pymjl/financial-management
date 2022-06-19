package cuit.pymjl;

import cuit.pymjl.remote.Server;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 22:09
 **/
public class ServerMain {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
