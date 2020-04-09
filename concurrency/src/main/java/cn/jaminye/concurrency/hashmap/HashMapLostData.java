package cn.jaminye.concurrency.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/08 15:37:01
 */

public class HashMapLostData {
  public static void main(String[] args) throws InterruptedException {
  ExecutorService executorService = Executors.newCachedThreadPool();
  final Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
executorService.submit(new Runnable() {
  
  @Override
  public void run() {
   for(int i=0;i<1000;i++) {
     hashMap.put(i, i);
   }
    
  }
});
executorService.submit(new Runnable() {
  
  @Override
  public void run() {
   for(int i=1000;i<2000;i++) {
     hashMap.put(i, i);
   }
    
  }
});


Thread.sleep(1000);
for(int i=0;i<hashMap.size();i++) {
  System.out.println(hashMap.get(i));
}
  }
}
