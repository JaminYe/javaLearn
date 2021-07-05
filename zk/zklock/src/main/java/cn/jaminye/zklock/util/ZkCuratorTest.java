package cn.jaminye.zklock.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用curator-framework
 *
 * @author Jamin
 * @date 2020/11/26 17:42
 */
@Slf4j
public class ZkCuratorTest {


	CuratorFramework curatorFramework;

	@Before
	public void init() {
		// 重试策略重试三次,每次时间比上次长
		// ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
		//重试三次，每次1秒
		RetryNTimes retryPolicy = new RetryNTimes(3, 1000);
		//每次1秒,最长5秒
		// RetryUntilElapsed retryPolicy = new RetryUntilElapsed(5000, 1000);
		curatorFramework = CuratorFrameworkFactory.builder().connectString("192.168.150.120:2181").sessionTimeoutMs(100000)
				.connectionTimeoutMs(100000).retryPolicy(retryPolicy).build();
		// CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.150.120:2181", retryPolicy);
		curatorFramework.start();
	}

	/**
	 * 创建节点
	 *
	 * @param
	 * @author Jamin
	 * @date 2020/11/27 15:04
	 */
	@SneakyThrows
	@Test
	public void createNode() {
		String s = curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/test", "test".getBytes());
		System.out.println(s);
	}

	/**
	 * 删除节点
	 */
	@Test
	@SneakyThrows
	public void deleteNode() {
		curatorFramework.delete().forPath("/test");
	}

	/**
	 * 获取子节点
	 *
	 * @param
	 * @author Jamin
	 * @date 2020/11/27 15:05
	 */
	@Test
	@SneakyThrows
	public void getChildrenNode() {
		List<String> childrenNodeList = curatorFramework.getChildren().forPath("/test");
		childrenNodeList.forEach(System.out::println);
	}

	/**
	 * 获取节点数据
	 *
	 * @param
	 * @author Jamin
	 * @date 2020/11/27 15:05
	 */
	@Test
	@SneakyThrows
	public void getData() {
		byte[] bytes = curatorFramework.getData().forPath("/test");
		System.out.println(new String(bytes));
	}

	/**
	 * 获取节点数据与状态
	 *
	 * @param
	 * @author Jamin
	 * @date 2020/11/27 15:05
	 */
	@Test
	@SneakyThrows
	public void getDataAndStat() {
		Stat stat = new Stat();
		byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/test");
		System.out.println(new String(bytes));
		System.out.println(stat);
	}

	/**
	 * 修改节点值
	 *
	 * @param
	 * @author Jamin
	 * @date 2020/11/27 15:18
	 */
	@Test
	@SneakyThrows
	public void setData() {
		curatorFramework.setData().forPath("/test", "testtest".getBytes());
	}

	/**
	 * 判断是否存在
	 * stat->null不存在
	 *
	 * @param
	 * @author Jamin
	 * @date 2020/11/27 15:26
	 */
	@Test
	@SneakyThrows
	public void checkExists() {
		Stat stat = curatorFramework.checkExists().forPath("/test");
		System.out.println(stat);
		Stat stat2 = curatorFramework.checkExists().forPath("/test11");
		System.out.println(stat2);
	}

	/**
	 * 异步操作
	 *
	 * @param
	 * @author Jamin
	 * @date 2020/11/27 15:30
	 */
	@SneakyThrows
	@Test
	public void async() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Stat stat = curatorFramework.checkExists().inBackground((curatorFramework, curatorEvent) -> {
			System.out.println(curatorEvent.getContext());
		}, "123457", executorService).forPath("/test");
		System.out.println(stat);
	}

	/**
	 * 节点监听
	 *
	 * @param
	 * @author Jamin
	 * @date 2020/11/27 15:30
	 */
	@SneakyThrows
	@Test
	public void watchNode() {
		String path = "/test";
		NodeCache nodeCache = new NodeCache(curatorFramework, path);
		nodeCache.start();
		nodeCache.getListenable().addListener(() -> System.out.println("监听到数据变化" + new String(nodeCache.getCurrentData().getData())));
		Thread.sleep(Integer.MAX_VALUE);


		/*PersistentWatcher persistentWatcher = new PersistentWatcher(curatorFramework, path, true);
		persistentWatcher.start();
		persistentWatcher.getListenable().addListener(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getPath());
			}
		});
		curatorFramework.setData().forPath(path, "1".getBytes());
		curatorFramework.setData().forPath(path, "6".getBytes());
		curatorFramework.setData().forPath(path, "5".getBytes());
		curatorFramework.setData().forPath(path, "3".getBytes());
		curatorFramework.setData().forPath(path, "2".getBytes());
		Thread.sleep(Integer.MAX_VALUE);*/
	}

	@SneakyThrows
	@Test
	public void watchChild() {
		/*String path = "/test";
		PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
		pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
		pathChildrenCache.getListenable().addListener((curatorFramework1, pathChildrenCacheEvent) -> {
			System.out.println(pathChildrenCacheEvent.getType() + "---------" + pathChildrenCacheEvent.getData().getPath() + "---------" +
					new String(pathChildrenCacheEvent.getData().getData()));
		});
		Thread.sleep(Integer.MAX_VALUE);*/


		// 可以监听当前节点和子节点
		CuratorCache curatorCache = CuratorCache.build(curatorFramework, "/test");
		curatorCache.start();
		curatorCache.listenable().addListener((type, childData, childData1) -> {
			System.out.println(type + "===============" + "==============" + childData.getPath() + "===================" +
					new String(childData.getData()) + "============" + new String(childData1.getData()));
		});
		Thread.sleep(Integer.MAX_VALUE);
	}
}



