package cn.jaminye.concurrency.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/** 
 * @description: 
 * @author: Jamin
 * @date: 2020/03/07 13:03:24
 */

public class AtomicStampedReferenceTest {
public static void main(String[] args) {
final AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(0, 0);

new Thread(new Runnable() {
  
  @Override
  public void run() {
    int stamp = atomicStampedReference.getStamp();
    System.out.println("线程："+Thread.currentThread().getName()+"版本号："+stamp+"当前值:"+atomicStampedReference.getReference());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if(atomicStampedReference.compareAndSet(0, 1, stamp, stamp+1)) {
      System.out.println("修改成功:"+stamp+"----"+atomicStampedReference.getReference());
    }else {
      System.out.println("修改失败");
    }
  }
},"主线程").start();






new Thread(new Runnable() {
  
  @Override
  public void run() {
    int stamp = atomicStampedReference.getStamp();
    System.out.println(Thread.currentThread().getName()+"版本-值"+stamp+"-"+atomicStampedReference.getReference());
    atomicStampedReference.compareAndSet(0, 1,stamp, stamp+1);
    System.out.println(Thread.currentThread().getName()+"版本-值"+atomicStampedReference.getStamp()+"-"+atomicStampedReference.getReference());
    int stamp2 = atomicStampedReference.getStamp();
    atomicStampedReference.compareAndSet(1, 0,stamp2, stamp2+1);
    System.out.println(Thread.currentThread().getName()+"版本-值"+atomicStampedReference.getStamp()+"-"+atomicStampedReference.getReference());
  }
},"干扰线程").start();
}
}
