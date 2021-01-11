package com.jamin.javalearn.aqs.countdownlaunch;


import java.util.concurrent.CountDownLatch;

public class SeeDoctorTask implements Runnable {

  
  
  private CountDownLatch countDownLatch;
  public SeeDoctorTask(CountDownLatch countDownLatch) {
    // TODO Auto-generated constructor stub
    this.countDownLatch=countDownLatch;
  }
  
  @Override
  public void run() {
    // TODO Auto-generated method stub
    try {
      System.out.println("排队看医生");
      Thread.sleep(2000);
      System.out.println("看医生结束");
    } catch (Exception e) {
      // TODO: handle exception
    }finally {
      if(countDownLatch!=null) {
        countDownLatch.countDown();
      }
    }
  }

}
