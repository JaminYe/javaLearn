package cn.jaminye.dubbo.server;

import cn.jaminye.base.User;
import cn.jaminye.base.UserService;

import java.lang.management.ManagementFactory;

/**
 * @author Jamin
 * @date 2020/8/11 7:05
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public User getName(String id) {
        User user = new User();
        user.setId(id);
        user.setName(ManagementFactory.getRuntimeMXBean().getName());
        user.setSex("man");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByName(String name, String sex) {
        User user = new User();
        user.setSex(sex);
        user.setName(ManagementFactory.getRuntimeMXBean().getName());
        return user;
    }
}
