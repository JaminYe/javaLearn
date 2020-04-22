package cn.jaminye.spring.iocsourcecode.config;

import cn.jaminye.spring.iocsourcecode.entity.Dog;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置文件
 *
 * @author Jamin
 * @date 2020/4/9 21:41
 */
@Configuration
@Import(Dog.class)
@ComponentScan("cn.jaminye.spring.iocsourcecode")
public class MainConfig {
}
