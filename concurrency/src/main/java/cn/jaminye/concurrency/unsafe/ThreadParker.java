package cn.jaminye.concurrency.unsafe;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/07 15:43:05
 */

public class ThreadParker {
  public static void main(String[] args) {


    LockSupport.unpark(Thread.currentThread());
    System.out.println("11111111");
    // 此处不会被阻塞
    LockSupport.park();
    System.out.println("22222222");
  }
}
