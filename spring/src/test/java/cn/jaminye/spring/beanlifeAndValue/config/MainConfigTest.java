package cn.jaminye.spring.beanlifeAndValue.config;

import cn.jaminye.spring.beanlifeAndValue.entity.Cat;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Jamin
 * @date 2020/4/5 11:45
 */
public class MainConfigTest {

  @Test
  public void testbeanInitDestory() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext(MainConfig.class);
    //    Person person = (Person) annotationConfigApplicationContext.getBean("person");
    //    Book book = (Book) annotationConfigApplicationContext.getBean(Book.class);
    Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
    System.out.println(cat);
    Cat cat1 = new Cat();
    annotationConfigApplicationContext.close();
  }
}
