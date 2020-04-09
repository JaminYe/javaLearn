package cn.jaminye.spring.beanlifeAndValue.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Jamin
 * @date 2020/4/5 11:40
 */
public class Person implements InitializingBean, DisposableBean {
  public void init() {
    System.out.println("person的init方法");
  }

  public void destory() {
    System.out.println("person的destory方法");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("接口销毁");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("接口初始化");
  }
}
