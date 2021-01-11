package cn.jaminye.concurrency.aqs.countdownlaunch;

import com.jamin.javalearn.aqs.countdownlaunch.QueueTask;
import com.jamin.javalearn.aqs.countdownlaunch.SeeDoctorTask;

import java.util.concurrent.CountDownLatch;

public class CountDownLaunchSample {
public static void main(String[] args) throws InterruptedException {
  CountDownLatch countDownLatch = new CountDownLatch(2);
  new Thread(new SeeDoctorTask(countDownLatch)).start();
  new Thread(new QueueTask(countDownLatch)).start();
  countDownLatch.await();
  System.out.println("结束回家");
}
}
