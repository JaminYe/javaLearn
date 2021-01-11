package cn.jaminye.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/06 22:35:51
 */

public class AtomicABAProblem {
  public static void main(String[] args) {
    final AtomicInteger atomicInteger = new AtomicInteger();
    new Thread(new Runnable() {

      @Override
      public void run() {
        System.out
            .println("线程:" + Thread.currentThread().getName() + "修改前的值" + atomicInteger.get());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        boolean compareAndSet = atomicInteger.compareAndSet(atomicInteger.get(), 2);
        if (compareAndSet) {
          System.out.println("修改成功" + "现在的值:" + atomicInteger.get());
        } else {
          System.out.println("修改失败");
        }

      }
    }, "主线程").start();



    new Thread(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        System.out.println("线程:" + Thread.currentThread().getName() + "修改前的值"+atomicInteger.get());
        atomicInteger.incrementAndGet();

        System.out
            .println("线程:" + Thread.currentThread().getName() + "第一次修改的值" + atomicInteger.get());
        atomicInteger.decrementAndGet();
        System.out
            .println("线程：" + Thread.currentThread().getName() + "第二次修改的值" + atomicInteger.get());

      }
    }, "干扰线程").start();


  }
}
