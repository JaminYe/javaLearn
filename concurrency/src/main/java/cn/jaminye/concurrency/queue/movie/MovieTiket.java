package cn.jaminye.concurrency.queue.movie;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/07 16:39:50
 */

public class MovieTiket implements Delayed {
  // 延迟时间
  private long delay;
  // 到期时间
  private long expire;
  // 消息
  private String msg;
  // 创建时间
  private long now;



  public long getDelay() {
    return delay;
  }



  public long getExpire() {
    return expire;
  }



  public String getMsg() {
    return msg;
  }



  public long getNow() {
    return now;
  }



  public MovieTiket(String msg, long delay) {
    this.delay = delay;
    this.msg = msg;
    this.expire = System.currentTimeMillis() + delay;
    this.now = System.currentTimeMillis();

  }



  @Override
  public int compareTo(Delayed o) {
    // TODO Auto-generated method stub
    return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
  }

  @Override
  public long getDelay(TimeUnit unit) {
    // TODO Auto-generated method stub
    return unit.convert(this.expire - System.currentTimeMillis(), unit.MILLISECONDS);
  }

}
