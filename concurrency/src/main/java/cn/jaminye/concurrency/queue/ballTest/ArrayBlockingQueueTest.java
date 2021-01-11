package cn.jaminye.concurrency.queue.ballTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/07 20:25:32
 */

public class ArrayBlockingQueueTest {

  private ArrayBlockingQueue<Ball> arrayBlockingQueue = new ArrayBlockingQueue<Ball>(1);


  public void produce(Ball ball) throws InterruptedException {
    arrayBlockingQueue.put(ball);
  }

  public int getBallNumber() {
    return arrayBlockingQueue.size();
  }


  public void consume() throws InterruptedException {
    arrayBlockingQueue.take();
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    final ArrayBlockingQueueTest arrayBlockingQueueTest = new ArrayBlockingQueueTest();
    executorService.submit(new Runnable() {

      @Override
      public void run() {
        int i = 0;
        while (true) {
          System.out.println("------------------------开始生产球");
          Ball ball = new Ball(i);
          try {
            arrayBlockingQueueTest.produce(ball);
            System.out.println("-----------------------放入完成---放入球编号为" + i + "------" + "箱子内共有"
                + arrayBlockingQueueTest.getBallNumber());
            i++;
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

      }
    });



    executorService.submit(new Runnable() {

      @Override
      public void run() {

        while (true) {
          try {
            System.out.println("****************************" + "准备取出球");
            arrayBlockingQueueTest.consume();
            System.out.println("*****************************取出完成"+ "箱子内共有"
                + arrayBlockingQueueTest.getBallNumber());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

      }
    });

    // executorService.shutdown();

  }
}
