package cn.jaminye.spring.beanAndCompent.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jamin
 * @date 2020/4/4 14:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MainConfigTest {

  @Test
  public void testBean() {
    ClassPathXmlApplicationContext classPathXmlApplicationContext =
        new ClassPathXmlApplicationContext("applicationContext.xml");
    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext(MainConfig.class);
    System.out.println(classPathXmlApplicationContext.getBean("person1"));
    System.out.println(annotationConfigApplicationContext.getBean("person"));
  }

  /** 包扫描测试 */
  @Test
  public void testComponentScan() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext(MainConfig.class);
    String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      System.out.println(beanDefinitionName);
    }
  }

  /** */
  @Test
  public void testScope() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext(MainConfig.class);
    /*Person person = (Person) annotationConfigApplicationContext.getBean("person");
    Person person2 = (Person) annotationConfigApplicationContext.getBean("person");
    System.out.println(person == person2);*/
    String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      System.out.println(beanDefinitionName);
    }
    System.out.println(annotationConfigApplicationContext.getBean("factoryBean"));
  }
}
