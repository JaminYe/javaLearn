package cn.jaminye.zklock.util;

/**
 * @author Jamin
 * @date 2020/11/23 10:39
 */
public class ZkLockTest {
	private static ZookeeperLock lock = new ZookeeperLock();

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				lock.lock();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}).start();
		}
	}
}
