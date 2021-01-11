package cn.jaminye.spring.iocsourcecode.beandefinitionregistrypostprocess;

import cn.jaminye.spring.iocsourcecode.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Jamin
 * @date 2020/4/9 22:25
 */
public class JaminBeanDefinitionRegistryPostProcessorTest {

  @Test
  public void test() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext(MainConfig.class);
  }
}
