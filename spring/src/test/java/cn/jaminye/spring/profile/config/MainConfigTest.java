package cn.jaminye.spring.profile.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Jamin
 * @date 2020/4/5 17:36
 */
public class MainConfigTest {
  @Test
  public void testProfile() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext();
    annotationConfigApplicationContext.getEnvironment().setActiveProfiles("test");
    annotationConfigApplicationContext.register(MainConfig.class);
    annotationConfigApplicationContext.refresh();
    String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      System.out.println(beanDefinitionName);
    }
  }
}
