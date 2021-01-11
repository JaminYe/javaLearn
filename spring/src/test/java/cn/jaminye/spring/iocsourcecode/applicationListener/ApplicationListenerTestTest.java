package cn.jaminye.spring.iocsourcecode.applicationListener;

import cn.jaminye.spring.iocsourcecode.config.MainConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试接受事件
 *
 * @author Jamin
 * @date 2020/4/9 21:39
 */
public class ApplicationListenerTestTest {
  @Test
  public void testApplicationListener() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext(MainConfig.class);
    annotationConfigApplicationContext.publishEvent(
        new ApplicationEvent("test发布一个事件") {
          @Override
          public Object getSource() {
            return super.getSource();
          }
        });
    annotationConfigApplicationContext.close();
  }
}
