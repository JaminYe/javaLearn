package cn.jaminye.zklock.util;

import cn.jaminye.zklock.entity.Lock;
import org.I0Itec.zkclient.ZkClient;

/**
 * @author Jamin
 * @date 2020/11/18 16:13
 */
public class ZookeeperLockUtil {

	private final String zkServer = "192.168.150.120:2181";
	private final String ROOT_PATH = "/lock";
	private ZkClient zkClient;

	public ZookeeperLockUtil() {
		zkClient = new ZkClient(zkServer, 5000, 20000);
		buildRoot();
	}

	public Lock lock(String lockId) {
		Lock lockNode = createLockNode(lockId);
		lockNode = tryActiveLock(lockNode);
		return null;
	}

	private Lock tryActiveLock(Lock lockNode) {
		return null;
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


	/**
	 * 创建锁节点
	 *
	 * @param lockId
	 * @return {@link cn.jaminye.zklock.entity.Lock}
	 * @author Jamin
	 * @date 2020/11/18 16:51
	 */
	public Lock createLockNode(String lockId) {
		String nodePath = zkClient.createEphemeralSequential(ROOT_PATH + "/" + lockId, "lock");
		return new Lock(lockId, nodePath);
	}
}
