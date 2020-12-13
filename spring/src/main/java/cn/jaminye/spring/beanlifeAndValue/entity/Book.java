package cn.jaminye.spring.beanlifeAndValue.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Jamin
 * @date 2020/4/5 15:41
 */
// @Component
public class Book {
  /** id */
  private int id;

  public Book() {
    System.out.println("book构造方法");
  }

  @PostConstruct
  public void init() {
    System.out.println("book init方法");
  }

  @PreDestroy
  public void destory() {
    System.out.println("book destory方法");
  }
}
