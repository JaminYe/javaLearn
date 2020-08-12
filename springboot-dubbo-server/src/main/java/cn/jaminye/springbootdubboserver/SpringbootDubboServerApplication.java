package cn.jaminye.springbootdubboserver;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class SpringbootDubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboServerApplication.class, args);
        System.out.println("服务已启动");
    }

}
