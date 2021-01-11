package cn.jaminye.concurrency.sysnc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试
 *
 * @author Jamin
 * @date 2020/3/30 9:13
 */
public class Test {

  public static void main(String[] args) {
    /**
     * 针对同一个对象<br>
     * 线程a是异步的 线程b在synchronized外是异步的.在内部是同步的,只能一个线程一个线程来<br>
     * 线程c整个方法区是同步的<br>
     */
    /*    SyncThread syncThread = new SyncThread();
    Thread a_thread1 = new Thread(syncThread, "A_Thread1");
    Thread b_thread1 = new Thread(syncThread, "B_Thread1");
    Thread c_thread1 = new Thread(syncThread, "C_Thread1");
    Thread a2_thread1 = new Thread(syncThread, "A_Thread2");
    Thread b2_thread1 = new Thread(syncThread, "B_Thread2");
    Thread c2_thread1 = new Thread(syncThread, "C_Thread2");*/

    /** 针对不同对象<br> */
    Thread a_thread1 = new Thread(new SyncThread(), "A_Thread1");
    Thread b_thread1 = new Thread(new SyncThread(), "B_Thread1");
    Thread c_thread1 = new Thread(new SyncThread(), "C_Thread1");
    Thread a2_thread1 = new Thread(new SyncThread(), "A_Thread2");
    Thread b2_thread1 = new Thread(new SyncThread(), "B_Thread2");
    Thread c2_thread1 = new Thread(new SyncThread(), "C_Thread2");

    //    a_thread1.start();
    //    b_thread1.start();
    c_thread1.start();
    //    a2_thread1.start();
    //    b2_thread1.start();
    c2_thread1.start();
  }

  static class SyncThread implements Runnable {

    @Override
    public void run() {
      String name = Thread.currentThread().getName();
      if (name.startsWith("A")) {
        async();
      } else if (name.startsWith("B")) {
        sync1();
      } else if (name.startsWith("C")) {
        sync2();
      }
    }

    private synchronized void sync2() {
      System.out.println(
          Thread.currentThread().getName()
              + "--sync2--"
              + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      System.out.println(
          Thread.currentThread().getName()
              + "---sync2--start-----"
              + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(
          Thread.currentThread().getName()
              + "---sync2--stop-----"
              + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    private void sync1() {
      System.out.println(
          Thread.currentThread().getName()
              + "--sysnc1--"
              + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      synchronized (this) {
        System.out.println(
            Thread.currentThread().getName()
                + "---sync1--start-----"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(
            Thread.currentThread().getName()
                + "--sysnc1---stop-----"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      }
    }

    private void async() {
      System.out.println(
          Thread.currentThread().getName()
              + "--async--"
              + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      System.out.println(
          Thread.currentThread().getName()
              + "-----async---start-----"
              + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(
          Thread.currentThread().getName()
              + "----async-stop-----"
              + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
  }
}
