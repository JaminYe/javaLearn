package cn.jaminye.zklock.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;

/**
 * @author Jamin
 * @date 2020/11/26 17:42
 */
public class ZkCurator {

	@Before
	public void init() {
		// 重试策略重试三次,每次时间比上次长
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
		//重试三次，每次1秒
		// RetryNTimes retryPolicy  = new RetryNTimes(3, 1000);
		//每次1秒,最长5秒
		// RetryUntilElapsed retryPolicy = new RetryUntilElapsed(5000, 1000);
		CuratorFramework client =
				CuratorFrameworkFactory.builder().connectString("192.168.150.120:2181").sessionTimeoutMs(5000)
						.connectionTimeoutMs(5000).retryPolicy(retryPolicy).build();
		// CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.150.120:2181",
		// exponentialBackoffRetry);
		client.start();

	}
}
