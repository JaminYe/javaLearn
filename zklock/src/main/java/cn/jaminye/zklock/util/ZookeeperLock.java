package cn.jaminye.zklock.util;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * @author Jamin
 * @date 2020/11/18 16:13
 */
public class ZookeeperLock implements Lock {
	private final String zkServer = "192.168.150.120:2181";
	private final String ROOT_PATH = "/lock";
	CountDownLatch countDownLatch = new CountDownLatch(1);
	private ZkClient zkClient;
	private volatile String beforeNodePath;
	private volatile String path;

	public ZookeeperLock() {
		zkClient = new ZkClient(zkServer, 5000, 20000);
		buildRoot();
	}

	public static void main(String[] args) {
		ZookeeperLock lock = new ZookeeperLock();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				lock.tryLock();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}).start();
		}
	}

	/**
	 * @param
	 * @author Jamin
	 * @date 2020/11/18 16:25
	 */
	private void buildRoot() {
		if (!zkClient.exists(ROOT_PATH)) {
			zkClient.createPersistent(ROOT_PATH);
		}
	}

	@Override
	public void lock() {
		if (tryLock()) {
			System.out.println("获得锁");
		} else {
			waitForLock();
			lock();
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public synchronized boolean tryLock() {
		path = zkClient.createEphemeralSequential(ROOT_PATH + "/", "lock");
		//获取子节点
		List<String> childrenNodes = zkClient.getChildren(ROOT_PATH).stream().sorted().collect(Collectors.toList());
		if (path.equals(childrenNodes.get(0))) {
			return true;
		} else {
			//监听前一个
			int i = Collections.binarySearch(childrenNodes, path.substring(ROOT_PATH.length() + 1));
			beforeNodePath = childrenNodes.get(i - 1);
		}
		return false;
	}

	public void waitForLock() {
		IZkDataListener iZkDataListener = new IZkDataListener() {
			@Override
			public void handleDataChange(String s, Object o) throws Exception {

			}

			@Override
			public void handleDataDeleted(String s) throws Exception {
				countDownLatch.countDown();
			}
		};
		zkClient.subscribeDataChanges(beforeNodePath, iZkDataListener);
		try {
			System.out.println("等待");
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		zkClient.unsubscribeDataChanges(beforeNodePath, iZkDataListener);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {
		zkClient.delete(path);
	}

	@Override
	public Condition newCondition() {
		return null;
	}
}
