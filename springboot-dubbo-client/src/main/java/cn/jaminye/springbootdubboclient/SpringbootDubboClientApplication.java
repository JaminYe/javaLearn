package cn.jaminye.springbootdubboclient;

import cn.jaminye.base.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableDubbo
@SpringBootApplication
public class SpringbootDubboClientApplication {
    @Reference(url = "dubbo://192.168.21.1:20881/cn.jaminye.base.UserService")
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboClientApplication.class, args).close();
    }

    @Bean
    public ApplicationRunner getBean() {
        return args -> {
            System.out.println(userService.getName("1"));
        };
    }
}
