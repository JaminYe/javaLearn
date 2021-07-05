package cn.jaminye.dubbo.rmi;

import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * rmi客户端
 *
 * @author Jamin
 * @date 2020/8/9 21:44
 */
public class RmiClient {
    public static void main(String[] args) {
        try {
            UserService userService = (UserService) Naming.lookup("rmi://localhost:8888/userService");
            String name = userService.getUserName("111");
            System.out.println(ManagementFactory.getRuntimeMXBean().getName());
            System.out.println(name);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }

    }
}
