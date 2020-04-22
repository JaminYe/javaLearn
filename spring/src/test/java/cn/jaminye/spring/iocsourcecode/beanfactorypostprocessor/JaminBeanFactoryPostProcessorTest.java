package cn.jaminye.spring.iocsourcecode.beanfactorypostprocessor;

import cn.jaminye.spring.iocsourcecode.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Jamin
 * @date 2020/4/9 22:03
 */
public class JaminBeanFactoryPostProcessorTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);
        annotationConfigApplicationContext.getBean("person");
        annotationConfigApplicationContext.close();
    }
}
