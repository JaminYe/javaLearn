package cn.jaminye.concurrency.threadpool;


import cn.jaminye.concurrency.threadpool.policy.DefaultPolicyHandler;

/**
 * 测试自定义线程池
 *
 * @author Jamin
 * @date 2020/3/25 10:15
 */
public class Test {
  public static void main(String[] args) {
    CustomizeThreadPoolExecutor customizeThreadPoolExecutor =
        new CustomizeThreadPoolExecutor(1, 1, 10, new DefaultPolicyHandler());
    for (int i = 0; i < 3; i++) {
      customizeThreadPoolExecutor.execute(
          new Runnable() {
            @Override
            public void run() {
              System.out.println(Thread.currentThread().getName());
            }
          });
    }
    customizeThreadPoolExecutor.shutdown();
  }
}
