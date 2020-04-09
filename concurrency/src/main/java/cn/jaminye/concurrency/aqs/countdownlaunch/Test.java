package com.jamin.javalearn.aqs.countdownlaunch;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/06 14:41:33
 */

public class Test {
  /*
   * public static void main(String[] args) { int count = 10000; CountDownLatch latch = new
   * CountDownLatch(count); for (int i = 0; i < count; i++) { new MyThread(latch).start(); } try {
   * latch.wait(); // ！！！！！ 注意这个方法 } catch (InterruptedException e) { e.printStackTrace(); }
   * System.out.println(Thread.currentThread().getName() + "主方法执行啦！"); }
   * 
   * }
   * 
   * 
   * class MyThread extends Thread {
   * 
   * private CountDownLatch countDownLatch;
   * 
   * public MyThread(CountDownLatch countDownLatch) { this.countDownLatch = countDownLatch; }
   * 
   * @Override public void run() { if (countDownLatch != null) { try { Thread.sleep(1000);
   * countDownLatch.countDown(); } catch (InterruptedException e) { e.printStackTrace(); } }
   * System.out.println(Thread.currentThread().getName() + "线程对象第一个输出"); }
   */



  public static void main(String[] args) {
    int num = 50;
    CyclicBarrier barrier = new CyclicBarrier(num);
    ExecutorService executor = Executors.newFixedThreadPool(num);
    for (int i = 0; i < num; i++) {
      executor.execute(new MyRunnable(barrier));
      System.out.println(Thread.currentThread().getName() + "循环添加线程进线程池");
    }
    System.out.println(Thread.currentThread().getName() + "主线程最后的挣扎！！！！");
  }
}


class MyRunnable implements Runnable {
  private CyclicBarrier barrier;

  public MyRunnable(CyclicBarrier barrier) {
    this.barrier = barrier;
  }

  @Override
  public void run() {
    try {
      System.out.println(Thread.currentThread().getName() + "CyclicBarrier await()执行之前！！！！");
      barrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + "CyclicBarrier await()执行之后！！！！");
  }
}
