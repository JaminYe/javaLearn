package cn.jaminye.zklock.util;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

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
	static CountDownLatch countDownLatch = new CountDownLatch(1);
	private final String zkServer = "192.168.150.120:2181";
	private final String ROOT_PATH = "/lock";
	private ZkClient zkClient;
	private volatile String beforeNodePath;
	private volatile String path;

	public ZookeeperLock() {
		zkClient = new ZkClient(zkServer, 5000, 20000);
		buildRoot();
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
			System.out.println(path);
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
		if (StringUtils.isBlank(path)) {
			path = zkClient.createEphemeralSequential(ROOT_PATH + "/", "lock");
		}
		//获取子节点
		List<String> childrenNodes = zkClient.getChildren(ROOT_PATH).stream().sorted().collect(Collectors.toList());
		if (path.equals(ROOT_PATH + "/" + childrenNodes.get(0))) {
			return true;
		} else {
			//监听前一个
			int i = Collections.binarySearch(childrenNodes, path.substring(ROOT_PATH.length() + 1));
			beforeNodePath = ROOT_PATH + "/" + childrenNodes.get(i - 1);
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
		if (zkClient.exists(beforeNodePath)) {
			try {
				System.out.println("等待");
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
