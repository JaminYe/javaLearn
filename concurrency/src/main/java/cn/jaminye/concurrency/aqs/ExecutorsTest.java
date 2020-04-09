package cn.jaminye.concurrency.aqs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/06 12:58:54
 */

public class ExecutorsTest {
  public static void main(String[] args) {
    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
    newScheduledThreadPool.schedule(new Runnable() {
      @Override
      public void run() {
        System.out.println("我在跑");
      }
    }, 3, TimeUnit.SECONDS);
    
    
    newScheduledThreadPool.schedule(new Runnable() {
      @Override
      public void run() {
        System.out.println("我是第二个");
        
      }
    }, 1, TimeUnit.SECONDS);
    
    
    newScheduledThreadPool.schedule(new Runnable() {
      @Override
      public void run() {
        System.out.println("我是第三个");
        
      }
    }, 8, TimeUnit.SECONDS);
    newScheduledThreadPool.shutdown();
  }
}
