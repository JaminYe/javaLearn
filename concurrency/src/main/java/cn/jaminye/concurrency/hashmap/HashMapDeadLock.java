package cn.jaminye.concurrency.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/08 11:11:58
 */

public class HashMapDeadLock {
  public static void main(String[] args) {
    for (int i = 0; i < 30; i++) {
      new Thread(new MapResizer()).start();
    }
  }


  public static class MapResizer implements Runnable {
    public static Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>(2);
    public static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
      // TODO Auto-generated method stub
      while (atomicInteger.get() < 10000) {
        hashMap.put(atomicInteger.get(), atomicInteger.get());
        atomicInteger.incrementAndGet();
      }
    }

  }
}
