package cuit.pymjl.remote;

import cuit.pymjl.entity.Condition;
import cuit.pymjl.entity.Request;
import cuit.pymjl.entity.Response;
import cuit.pymjl.entity.User;

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
}
