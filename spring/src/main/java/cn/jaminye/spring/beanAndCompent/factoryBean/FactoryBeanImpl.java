package cn.jaminye.spring.beanAndCompent.factoryBean;

import cn.jaminye.spring.beanAndCompent.entity.Dog;
import org.springframework.beans.factory.FactoryBean;

/**
 * 实现factoryBean往IOC容器添加组件
 *
 * @author Jamin
 * @date 2020/4/5 11:07
 */
public class FactoryBeanImpl implements FactoryBean<Dog> {
  @Override
  public Dog getObject() throws Exception {
    return new Dog();
  }

  @Override
  public Class<?> getObjectType() {
    return Dog.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
