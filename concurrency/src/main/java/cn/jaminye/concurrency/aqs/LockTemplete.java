
package cn.jaminye.concurrency.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @description:
 * @author: Jamin
 * @date: 2020年3月6日下午12:52:31
 */
public class LockTemplete {
  private Integer counter = 0;
  private ReentrantLock lock = new ReentrantLock(true);


  /**
   * @description:
   * @param threadName
   */
  public void modifyResources(String threadName) {

    System.out.println("线程" + threadName + "启动");
    lock.lock();
    System.out.println("线程" + threadName + "第一次加锁");
    counter++;
    System.out.println("第" + counter + "次业务代码");
    lock.lock();
    System.out.println("线程" + threadName + "第二次加锁");
    counter++;
    System.out.println("第" + counter + "次业务代码");
    lock.unlock();
    System.out.println("第一次释放锁");
    lock.unlock();
    System.out.println("第二次释放锁");
  }


  public static void main(String[] args) {
    LockTemplete lockTemplete = new LockTemplete();
    new Thread(() -> {
      String name = Thread.currentThread().getName();
      lockTemplete.modifyResources(name);
    }).start();
  }



}
