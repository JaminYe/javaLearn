package cn.jaminye.spring.beanAndCompent.config;

import cn.jaminye.spring.beanAndCompent.entity.Person;
import cn.jaminye.spring.beanAndCompent.factoryBean.FactoryBeanImpl;
import cn.jaminye.spring.beanAndCompent.service.ServiceTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 配置文件
 *
 * @author Jamin
 * @date 2020/4/4 13:38
 */
@Configuration
/** 基础扫描mainConfig controllerTest serviceTest person */
// @ComponentScan(basePackages = "cn.jaminye.spring")

/** 排除扫描 排除Controoler注解,排除ServiceTest类型 mainConfig serviceTest2 person */
@ComponentScan(
    basePackages = "cn.jaminye.spring",
    excludeFilters = {
      @ComponentScan.Filter(
          type = FilterType.ANNOTATION,
          value = {Controller.class, Service.class, Repository.class}),
      @ComponentScan.Filter(
          type = FilterType.ASSIGNABLE_TYPE,
          value = {ServiceTest.class})
    })
/** 包含扫描,只包含Controller不使用默认过滤器 mainConfig controllerTest person */
/*@ComponentScan(
basePackages = "cn.jaminye.spring",
includeFilters = {
  @ComponentScan.Filter(
      type = FilterType.ANNOTATION,
      value = {Controller.class})
},
useDefaultFilters = false)*/
/** 自定义扫描过滤器 */
/*@ComponentScan(
basePackages = "cn.jaminye.spring",
includeFilters = {
  @ComponentScan.Filter(
      type = FilterType.ANNOTATION,
      value = {Repository.class})
},
excludeFilters = {
  @ComponentScan.Filter(
      type = FilterType.CUSTOM,
      value = {cn.jaminye.spring.filterType.FilterType.class})
})*/

// @Import(value = ImportSelectorImpl.class)
// @Import(value = ImportBeanDefinitionRegistrarImpl.class)
public class MainConfig {

  //  @Bean
  //  @Lazy
  //  @Scope(scopeName = "prototype")
  public Person person() {
    return new Person();
  }

  /*  @Bean
  @Conditional(CustomizeCondition.class)
  public Dog dog() {
    return new Dog();
  }*/

  @Bean
  public FactoryBeanImpl factoryBean() {
    return new FactoryBeanImpl();
  }
}
