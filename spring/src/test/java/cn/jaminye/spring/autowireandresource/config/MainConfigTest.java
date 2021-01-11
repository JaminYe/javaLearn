package cn.jaminye.spring.autowireandresource.config;

import cn.jaminye.spring.autowireandresource.service.TestService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Jamin
 * @date 2020/4/7 20:34
 */
public class MainConfigTest {

  @Test
  public void testAutoWireAndResource() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext(MainConfig.class);
    System.out.println(
        ((TestService) annotationConfigApplicationContext.getBean("testService")).toString());
  }
}
