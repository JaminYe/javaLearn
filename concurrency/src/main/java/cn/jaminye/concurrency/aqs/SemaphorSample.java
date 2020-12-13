package cn.jaminye.concurrency.aqs;
import java.util.concurrent.Semaphore;

public class SemaphorSample {
  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(2);
    for (int i = 0; i < 5; i++) {
      new Thread(new Task(semaphore, "Thread" + i)).start();
    }
  }

  public static class Task extends Thread {
    Semaphore semaphor;

    public Task(Semaphore semaphore, String tName) {
      this.semaphor = semaphore;
      this.setName(tName);
    }

    @Override
    public void run() {
      try {
        semaphor.acquire();
        System.out.println("当前获得锁的对象:" + Thread.currentThread().getName());
        Thread.sleep(100);
        semaphor.release();
        System.out.println("当前释放锁的对象" + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
