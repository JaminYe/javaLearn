package cn.jaminye.spring.iocsourcecode.beandefinitionregistrypostprocess;

import cn.jaminye.spring.iocsourcecode.entity.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2020/4/9 22:18
 */
@Component
public class JaminBeanDefinitionRegistryPostProcessor
    implements BeanDefinitionRegistryPostProcessor {
  /** 在bean扫描后调用 */
  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry)
      throws BeansException {
    System.out.println("bean扫描之前数量" + beanDefinitionRegistry.getBeanDefinitionCount());
    beanDefinitionRegistry.registerBeanDefinition("dog", new RootBeanDefinition(Dog.class));
  }

  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    System.out.println("bean扫描之后数量" + configurableListableBeanFactory.getBeanDefinitionCount());
  }
}
