package cn.jaminye.concurrency.volatitle;

/**
 * @author Jamin
 * @date 2020/8/1 17:05
 */
public class VolatitleVisibilitySample {
    public boolean flag = false;

    public static void main(String[] args) {
        VolatitleVisibilitySample volatitleVisibilitySample = new VolatitleVisibilitySample();

        new Thread(new Runnable() {
            @Override
            public void run() {
                volatitleVisibilitySample.load();
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                volatitleVisibilitySample.refresh();
            }
        }).start();
    }

    public void refresh() {
        flag = true;

    }

    public void load() {
        while (!flag) {
        }
        System.out.println("值改变了");
    }


}
