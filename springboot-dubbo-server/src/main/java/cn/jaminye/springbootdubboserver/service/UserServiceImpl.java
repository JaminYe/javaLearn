package cn.jaminye.springbootdubboserver.service;

import cn.jaminye.base.User;
import cn.jaminye.base.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

/**
 * @author Jamin
 * @date 2020/8/11 7:05
 */
@Service
@Component
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
