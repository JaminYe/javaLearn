package com.jamin.javalearn.aqs.countdownlaunch;


import java.util.concurrent.CountDownLatch;

public class QueueTask implements Runnable {
  private CountDownLatch countDownLatch;



  public QueueTask(CountDownLatch cDownLatch) {
    this.countDownLatch = cDownLatch;
  }

  @Override
  public void run() {
    try {
      System.out.println("开始排队买药！！！！");
      Thread.sleep(5000);
      System.out.println("排队结束");
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }finally {
      if(countDownLatch!=null) {
        countDownLatch.countDown();
      }
    }


  }

}
