package cn.jaminye.spring.autowireandresource.config;

import cn.jaminye.spring.autowireandresource.entity.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jamin
 * @date 2020/4/7 20:33
 */
@ComponentScan(basePackages = "cn.jaminye.spring.autowireandresource")
@Configuration
public class MainConfig {

  @Bean
  public Test test() {
    Test test = new Test();
    test.setName("测试0");
    return test;
  }

  @Bean
  public Test test1() {
    Test test = new Test();
    test.setName("测试1");
    return test;
  }
}
