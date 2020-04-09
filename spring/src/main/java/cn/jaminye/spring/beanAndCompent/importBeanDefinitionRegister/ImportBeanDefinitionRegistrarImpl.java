package cn.jaminye.spring.beanAndCompent.importBeanDefinitionRegister;

import cn.jaminye.spring.beanAndCompent.entity.Dog;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Jamin
 * @date 2020/4/5 10:55
 * @deprecated 通过ImportBeanDefinitionRegistrar接口添加组件
 */
public class ImportBeanDefinitionRegistrarImpl implements ImportBeanDefinitionRegistrar {
  @Override
  public void registerBeanDefinitions(
      AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
    RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Dog.class);
    beanDefinitionRegistry.registerBeanDefinition("dog", rootBeanDefinition);
  }
}
