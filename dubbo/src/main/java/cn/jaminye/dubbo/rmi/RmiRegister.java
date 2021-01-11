package cn.jaminye.dubbo.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 注册中心
 *
 * @author Jamin
 * @date 2020/8/9 12:47
 */
public class RmiRegister {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(8888);
            System.out.println("注册中心已启动");
            System.in.read();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
