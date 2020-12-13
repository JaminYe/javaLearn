package cn.jaminye.spring.beanlifeAndValue.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Jamin
 * @date 2020/4/5 15:56
 */
// @Component
public class Dog implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
    System.out.println("postProcessBeforeInitialization-------" + s);

    return o;
  }

  @Override
  public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
    System.out.println("postProcessAfterInitialization--------------" + s);

    return o;
  }

  @PostConstruct
  public void init() {
    System.out.println("init方法");
  }

  @PreDestroy
  public void destory() {
    System.out.println("destory方法");
  }
}
