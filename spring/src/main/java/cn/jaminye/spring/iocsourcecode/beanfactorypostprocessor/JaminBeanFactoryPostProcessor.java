package cn.jaminye.spring.iocsourcecode.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 主要用途在bean解析直接还没实例化之前修改bean
 *
 * @author Jamin
 * @date 2020/4/9 21:55
 */
// @Component
public class JaminBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    System.out.println("调用postProcessBeanFactory");
    for (String beanDefinitionName : configurableListableBeanFactory.getBeanDefinitionNames()) {
      //      System.out.println(beanDefinitionName);

      if ("dog".equals(beanDefinitionName)) {
        BeanDefinition beanDefinition =
            configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
        beanDefinition.setLazyInit(true);
      }
    }
  }
}
