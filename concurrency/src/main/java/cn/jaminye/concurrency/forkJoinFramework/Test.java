package cn.jaminye.concurrency.forkJoinFramework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 测试
 *
 * @author Jamin
 * @date 2020/4/1 8:45
 */
public class Test {

  public static void main(String[] args) throws InterruptedException {

    long start = System.currentTimeMillis();
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    PrintTaskAction printTaskAction = new PrintTaskAction(0, 3000000);
    forkJoinPool.submit(printTaskAction);
    forkJoinPool.awaitTermination(100, TimeUnit.SECONDS);
    forkJoinPool.shutdown();
    long end = System.currentTimeMillis();
    System.out.println("time:" + (end - start));
    //    single();
  }

  public static void single() {
    long start = System.currentTimeMillis();
    for (int i = 0; i < 30000000; i++) {
      System.out.println(Thread.currentThread().getName() + i);
    }
    long end = System.currentTimeMillis();
    System.out.println("time:" + (end - start));
  }

  static class PrintTaskAction extends RecursiveAction {
    private final int THRESHOLD = 50;
    private int start;
    private int end;

    public PrintTaskAction(int start, int end) {
      super();
      this.start = start;
      this.end = end;
    }

    @Override
    protected void compute() {

      if (end - start < THRESHOLD) {
        for (int i = start; i < end; i++) {
          System.out.println(Thread.currentThread().getName() + "------" + i);
        }
      } else {
        int temp = (end - start) / 2;
        PrintTaskAction printTaskAction = new PrintTaskAction(start, temp);
        PrintTaskAction printTaskAction1 = new PrintTaskAction(temp, end);
        printTaskAction.fork();
        printTaskAction1.fork();
      }
    }
  }
}
