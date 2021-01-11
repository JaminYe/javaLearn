package cn.jaminye.concurrency.threadpool;


import cn.jaminye.concurrency.threadpool.policy.PolicyHandler;
import com.sun.xml.internal.ws.policy.PolicyException;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义线程池
 *
 * @author Jamin
 * @date 2020/03/15 11:11:51
 */
public class CustomizeThreadPoolExecutor {

  /** 线程池最大的大小 */
  private static final Integer MAX_POOL_SIZE = 50;

  static AtomicInteger atomicInteger = new AtomicInteger();
  /** 等待队列 */
  private final BlockingQueue<Runnable> workQueue;
  /** 锁 */
  private final ReentrantLock mainLock = new ReentrantLock();
  /** 线程池的大小 */
  private volatile int poolSizes;
  /** 存活时间 */
  private volatile long keepAliveTime;
  /** 是否允许超时 */
  private volatile boolean allowThreadTimeOut;
  /** 拒绝策略 */
  private volatile PolicyHandler handler;
  /** 当前激活线程数 */
  private AtomicInteger ctl = new AtomicInteger();
  /** 是否已经中断 */
  private volatile boolean isShutDown = false;
  /** 总完成任务量 */
  private long completedTaskCount;

  private HashSet<Worker> workers = new HashSet<Worker>();

  public CustomizeThreadPoolExecutor(
      int poolsizes, int queueSize, long keepAliveTime, PolicyHandler policyHandler) {
    if (poolsizes <= 0 || poolsizes > MAX_POOL_SIZE) {
      throw new IllegalArgumentException("线程池的大小必须大于0");
    }
    this.poolSizes = poolsizes;
    this.keepAliveTime = keepAliveTime;
    if (keepAliveTime > 0) {
      allowThreadTimeOut = true;
    }
    this.workQueue = new ArrayBlockingQueue<>(queueSize);
    this.handler = policyHandler;
  }

  /**
   * 执行任务
   *
   * @param task 任务
   */
  public void execute(Runnable task) {
    if (task == null) {
      throw new NullPointerException("任务不得为空");
    }
    if (isShutDown) {
      throw new IllegalStateException("线程池已销毁,禁止提交任务.........");
    }
    int c = ctl.get();
    if (c < poolSizes) {
      if (addWorker(task, true)) {
        return;
      }
    } else if (workQueue.offer(task)) {
      System.out.println("添加到了阻塞队列");

    } else {
      try {
        handler.rejected(task, this);
      } catch (PolicyException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 是否启动线程执行任务or放入
   *
   * @param r 任务
   * @param startNew 是否激活
   * @return
   */
  private boolean addWorker(Runnable r, boolean startNew) {

    if (startNew) {
      ctl.incrementAndGet();
    }
    Worker worker = new Worker(r);
    Thread thread = worker.thread;
    boolean workAdded = false;
    boolean workStarted = false;
    if (thread != null) {
      ReentrantLock mainLock = this.mainLock;
      mainLock.lock();

      try {
        if (!isShutDown) {
          if (thread.isAlive()) {
            throw new IllegalThreadStateException();
          }
          workers.add(worker);
          workAdded = true;
        }
      } finally {
        if (workAdded) {
          thread.start();
          workStarted = true;
        }
        mainLock.unlock();
      }
    }
    return workStarted;
  }

  private Runnable getTask() {
    try {
      System.out.println(allowThreadTimeOut);
      System.out.println(keepAliveTime);

      return allowThreadTimeOut
          ? workQueue.poll(keepAliveTime, TimeUnit.SECONDS)
          : workQueue.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void runWorker(Worker worker) {
    Thread thread = Thread.currentThread();
    Runnable task = worker.firstTask;
    worker.firstTask = null;
    boolean completedAbruptly = false;
    try {
      while (task != null || (task = getTask()) != null) {
        worker.lock();
        task.run();
        //        置空任务
        task = null;
      }
      worker.completedTask++;
      completedAbruptly = false;
      worker.unlock();
    } finally {
      processWorkExit(worker, completedAbruptly);
    }
  }

  private void processWorkExit(Worker worker, boolean completedAbruptly) {
    if (completedAbruptly) {
      ctl.decrementAndGet();
    }
    ReentrantLock mainLock = this.mainLock;
    mainLock.lock();
    try {
      completedTaskCount += worker.completedTask;
      workers.remove(worker);
    } finally {
      mainLock.unlock();
    }
    if (!workQueue.isEmpty()) {
      addWorker(null, false);
    }
    //    //    直接中断
    //    if (ctl.get() < poolSizes) {
    //      worker.thread.interrupt();
    //    }
  }
  /** 线程池关闭 */
  private void shutDown() {
    ReentrantLock mainLock = this.mainLock;
    mainLock.lock();
    try {
      workers.stream()
          .forEach(
              worker -> {
                Thread thread = worker.thread;
                if (!thread.isInterrupted() && worker.tryLock()) {
                  try {
                    thread.interrupt();
                  } catch (Exception e) {
                    e.printStackTrace();
                  } finally {
                    worker.unlock();
                  }
                }
              });

    } finally {
      mainLock.unlock();
    }
  }

  public void shutdown() {}

  class Worker extends ReentrantLock implements Runnable {
    final Thread thread;
    volatile long completedTask;
    Runnable firstTask;

    Worker(Runnable r) {
      this.firstTask = r;
      this.thread = new Thread(this, "thread-name-" + atomicInteger.incrementAndGet());
    }

    @Override
    public void run() {
      runWorker(this);
    }
  }
}
