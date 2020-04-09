package cn.jaminye.concurrency.aqs;



import java.util.concurrent.Exchanger;

/**
 * 
 * @author Jamin
 * @date 2020年3月6日 下午12:46:49
 * @desc
 */
public class ExchangerTest {
  /**
   */
  public static void main(String[] args) {
    final Exchanger<Integer> exchanger = new Exchanger<>();
    for (int i = 0; i < 10; i++) {
      final Integer num = i;
      new Thread() {

        @Override
        public void run() {
          try {
            Integer exchangeNum = exchanger.exchange(num);
            Thread.sleep(1000);
            System.out.println(
                "线程" + Thread.currentThread().getName() + "原先数据" + num + "现在数据" + exchangeNum);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }



      }.start();
    }
  }
}
