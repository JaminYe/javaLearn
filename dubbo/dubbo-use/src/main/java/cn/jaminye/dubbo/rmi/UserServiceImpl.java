package cn.jaminye.dubbo.rmi;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * 用户接口实现
 *
 * @author Jamin
 * @date 2020/8/9 12:45
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService, Serializable {
    protected UserServiceImpl() throws RemoteException {
    }

    protected UserServiceImpl(int port) throws RemoteException {
        super(port);
    }

    protected UserServiceImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public String getUserName(String id) {
        return "服务调用完成" + ManagementFactory.getRuntimeMXBean().getName();
    }
}
