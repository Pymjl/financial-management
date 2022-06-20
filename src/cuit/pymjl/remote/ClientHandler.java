package cuit.pymjl.remote;

import cuit.pymjl.entity.*;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 23:25
 **/
public class ClientHandler {
    public Response invoke(Integer option, Integer group, User user) {
        Request request = new Request(option, user, group);
        Client client = new Client();
        return (Response) client.sendRequest(request);
    }

    public Response invoke(Integer option, Integer group, String username) {
        Request request = new Request(option, username, group);
        Client client = new Client();
        return (Response) client.sendRequest(request);
    }

    public Response invoke(int option, Integer group, Condition condition) {
        Request request = new Request(option, condition, group);
        Client client = new Client();
        return (Response) client.sendRequest(request);
    }

    public Response invoke(int option, Integer group, Bill bill) {
        Request request = new Request(option, bill, group);
        Client client = new Client();
        return (Response) client.sendRequest(request);
    }

    public Response invoke(int option, Integer group, int id) {
        Request request = new Request(option, id, group);
        Client client = new Client();
        return (Response) client.sendRequest(request);
    }

    public Response invoke(int option, Integer group, Key keywords) {
        Request request = new Request(option, keywords, group);
        Client client = new Client();
        return (Response) client.sendRequest(request);
    }
}
