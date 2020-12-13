package cn.jaminye.dubbo.rmi;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * rmi服务端
 *
 * @author Jamin
 * @date 2020/8/9 20:10
 */
public class RmiUserServer {
    public static void main(String[] args) throws RemoteException {
        UserService userService = new UserServiceImpl();
        try {
            Naming.bind("rmi://localhost:8888/userService", userService);
            System.out.println("服务端启动成功");
            System.in.read();
        } catch (AlreadyBoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
