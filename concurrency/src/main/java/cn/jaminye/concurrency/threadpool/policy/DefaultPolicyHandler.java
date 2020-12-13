package cn.jaminye.concurrency.threadpool.policy;

import cn.jaminye.concurrency.threadpool.CustomizeThreadPoolExecutor;
import com.sun.xml.internal.ws.policy.PolicyException;

/**
 * 默认策略
 *
 * @author Jamin
 * @date 2020/3/15 11:31
 */
public class DefaultPolicyHandler implements PolicyHandler {

  public DefaultPolicyHandler() {}

  @Override
  public void rejected(Runnable task, CustomizeThreadPoolExecutor executor) throws PolicyException {
    System.out.println(" 任务已满");

    throw new PolicyException("任务已满");
  }
}
