package cn.jaminye.concurrency.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierSample implements Runnable{

  
  private CyclicBarrier cyclicBarrier;
  private int index;
  public  CyclicBarrierSample(CyclicBarrier cyclicBarrier,int index) {
  this.cyclicBarrier=cyclicBarrier;
  this.index=index;
  } 
  @Override
  public void run() {
    try {
      System.out.println("index:"+index);
      index--;
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  
  
  
  public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
   CyclicBarrier cyclicBarrier2 = new CyclicBarrier(11);
   for (int i = 0; i < 10; i++) {
    new Thread(new CyclicBarrierSample(cyclicBarrier2,i)).start();
  }
   cyclicBarrier2.await();
   System.out.println("集合完毕");
  }
  
  
  

}
