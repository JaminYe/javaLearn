package cn.jaminye.spring.iocsourcecode.applicationListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 创建监听器
 *
 * @author Jamin
 * @date 2020/4/9 21:37
 */
// @Component
public class ApplicationListenerTest implements ApplicationListener {
  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    System.out.println("我接受到了一个事件" + event);
  }
}
