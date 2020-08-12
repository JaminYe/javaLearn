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
    public User getName(String id) {
        User user = new User();
        user.setId(id);
        user.setName(ManagementFactory.getRuntimeMXBean().getName());
        user.setSex("man");
        return user;
    }
}
