package cn.jaminye.dubbo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 用户接口
 *
 * @author Jamin
 * @date 2020/8/9 12:37
 */
public interface UserService extends Remote {
    /**
     * 获取用户名
     *
     * @author Jamin
     * @date 2020/8/9 12:45
     */
    String getUserName(String id) throws RemoteException;

    ;

}
