package cn.jaminye.concurrency.queue.movie;

import java.util.concurrent.DelayQueue;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/07 16:52:33
 */

public class DelayedQueueTest {
  public static void main(String[] args) {
    DelayQueue<MovieTiket> delayQueue = new DelayQueue<MovieTiket>();
    MovieTiket movieTiket0 = new MovieTiket("电影票0", 8000);
    MovieTiket movieTiket1 = new MovieTiket("电影票1", 5000);
    MovieTiket movieTiket2 = new MovieTiket("电影票2", 1000);
    MovieTiket movieTiket3 = new MovieTiket("电影票3", 10000);

    delayQueue.put(movieTiket0);
    delayQueue.put(movieTiket1);
    delayQueue.put(movieTiket2);
    delayQueue.put(movieTiket3);
    while (delayQueue.size() > 0) {
try {
  MovieTiket take = delayQueue.take();
  System.out.println(take.getMsg());
} catch (InterruptedException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
}


    }
  }
}
