package cn.jaminye.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/** 
 * @description: 
 * @author: Jamin
 * @date: 2020/03/06 21:04:23
 */

public class AtomicTest {
public static void main(String[] args) throws InterruptedException {
  final AtomicInteger atomicInteger = new AtomicInteger();

  for (int i = 0; i <10; i++) {
    new Thread(new Runnable() {
      
      @Override
      public void run() {
        // TODO Auto-generated method stub
        atomicInteger.incrementAndGet();
      }
    }).start();
  }
  
  
//  上面10个线程还没跑完
  Thread.sleep(1000);
  System.out.println(atomicInteger.get());

}
}
