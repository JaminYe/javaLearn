package cn.jaminye.dubbo.springdubbo;

import cn.jaminye.base.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring-dubbo-client
 *
 * @author Jamin
 * @date 2020/8/12 22:00
 */
public class SpringDubboClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.getName("111"));
    }
}
