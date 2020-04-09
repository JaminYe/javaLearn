package cn.jaminye.concurrency.threadpool.policy;

import cn.jaminye.concurrency.threadpool.CustomizeThreadPoolExecutor;

import com.sun.xml.internal.ws.policy.PolicyException;

/**
 * @author Jamin <br>
 * @date 2020/3/15 11:30<br>
 * @desc 策略接口
 */
public interface PolicyHandler {

  /**
   * 拒接策略
   *
   * @param task
   * @param executor
   */
  void rejected(Runnable task, CustomizeThreadPoolExecutor executor) throws PolicyException;
}
