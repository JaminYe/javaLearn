package cn.jaminye.zookeeper.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamin
 * @date 2020/9/15 18:05
 */
@Slf4j
public class Main {

	ZooKeeper zooKeeper;

	@Test
	public void getChild() throws KeeperException, InterruptedException {
		List<String> children = zooKeeper.getChildren("/jamin3", event -> {
			System.out.println(event.getPath());
		});
		children.stream().forEach(System.out::println);
		Thread.sleep(Integer.MAX_VALUE);

	}

	@Before
	public void init() throws IOException {
		zooKeeper = new ZooKeeper("192.168.150.120", 10000, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getPath());
				System.out.println(event);
			}
		});
	}

	/**
	 * 获取值
	 *
	 * @author Jamin
	 * @date 2020/9/15 22:11
	 */
	@Test
	public void getData() throws KeeperException, InterruptedException {
		byte[] data = zooKeeper.getData("/jamin3", false, null);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "-----" + new String(data));
	}

	/**
	 * 监听
	 *
	 * @author Jamin
	 * @date 2020/9/15 22:12
	 */
	@Test
	public void getDataWatch() throws KeeperException, InterruptedException {
		byte[] data = zooKeeper.getData("/jamin", true, null);
		System.out.println(Thread.currentThread().getStackTrace()[0].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "-----" + new String(data));

		Thread.sleep(Integer.MAX_VALUE);
	}

	/**
	 * 自定义监听   持续监听
	 *
	 * @author Jamin
	 * @date 2020/9/15 22:12
	 */
	@Test
	public void getDataWatchMe() throws KeeperException, InterruptedException {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
		Stat stat = new Stat();
		byte[] data = zooKeeper.getData("/jamin3", new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				log.info("触发watch" + event.getPath());
				try {
					zooKeeper.getData(event.getPath(), this, null);
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, stat);
		log.debug("data" + new String(data));
		log.debug("stat" + "-------------" + stat);
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Test
	public void testDataCallback() throws InterruptedException {
		zooKeeper.getData("/jamin5", false, (rc, path, ctx, data, stat) -> {
			//rc获取data是否成功
			System.out.println(rc);
			// ctx-->123
		}, "123");
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Test
	public void testCreate() throws KeeperException, InterruptedException {
		List<ACL> list = new ArrayList<>();
		list.add(new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.ADMIN, new Id("world", "anyone")));
		String s = zooKeeper.create("/jamin3/jamin2", "test".getBytes(), list, CreateMode.CONTAINER);
		System.out.println(s);
	}


}
