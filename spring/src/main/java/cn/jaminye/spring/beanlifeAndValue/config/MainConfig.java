package cn.jaminye.spring.beanlifeAndValue.config;

import cn.jaminye.spring.beanlifeAndValue.entity.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Jamin
 * @date 2020/4/5 11:38
 */
@Configuration
@ComponentScan(basePackages = "cn.jaminye.spring")
@PropertySource(value = "cat.properties", encoding = "utf-8")
public class MainConfig {

  //  @Bean
  //  @Scope(scopeName = "prototype")
  public Person person() {
    return new Person();
  }
}
