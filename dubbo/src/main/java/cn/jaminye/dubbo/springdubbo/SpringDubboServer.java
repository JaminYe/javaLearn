package cn.jaminye.dubbo.springdubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * spring-dubbo-server
 *
 * @author Jamin
 * @date 2020/8/12 21:52
 */
public class SpringDubboServer {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.start();
        System.out.println("服务暴露完成");
        System.in.read();
    }
}
