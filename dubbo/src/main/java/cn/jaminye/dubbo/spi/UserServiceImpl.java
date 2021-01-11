package cn.jaminye.dubbo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Jamin
 * @date 2020/8/10 22:34
 */
public class UserServiceImpl implements UserService {
    public static void main(String[] args) {
        Iterator<UserService> services = ServiceLoader.load(UserService.class).iterator();
        UserService userService = null;
        while (services.hasNext()) {
            userService = services.next();
        }
        System.out.println(userService.getName("11"));
    }

    @Override
    public String getName(String id) {
        System.out.println("测试");
        return id;
    }
}
