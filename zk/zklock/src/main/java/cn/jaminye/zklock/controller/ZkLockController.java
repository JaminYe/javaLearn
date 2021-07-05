package cn.jaminye.zklock.controller;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author Jamin
 * @date 2020/11/30 10:36
 */
@Controller
public class ZkLockController {
	static CuratorFramework curatorFramework;
	//重试3次每次1秒钟
	static RetryNTimes retryPolicy = new RetryNTimes(3, 1000);

	static {
		//建立与zk的连接
		curatorFramework = CuratorFrameworkFactory.builder().connectString("192.168.150.120:2181").sessionTimeoutMs(100000)
				.connectionTimeoutMs(100000).retryPolicy(retryPolicy).build();
		curatorFramework.start();
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@ResponseBody
	@RequestMapping("reduceInventory")
	public String reduceInventory() {
		//不可重入独占锁
		// InterProcessSemaphoreMutex interProcessSemaphoreMutex = new InterProcessSemaphoreMutex(curatorFramework, "/lock");
		//可重入独占锁
		InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/lock");
		try {
			//获得锁
			if (lock.acquire(5, TimeUnit.SECONDS)) {
				String sql = "select counts from test";
				//查库存
				int i = jdbcTemplate.queryForObject(sql, Integer.class).intValue();
				if (i > 0) {
					System.out.println(i);
					//减库存
					String sql1 = "update test set counts=counts-1 where id=1";
					jdbcTemplate.update(sql1);
					System.out.println("减少库存");
				}
				//释放锁
				lock.release();
			} else {
				return "请稍后再试";
			}
		} catch (Exception e) {
			System.out.println("获取锁失败");
			return "failed";
		}
		return "success";
	}


	@RequestMapping("test")
	@ResponseBody
	public String test(HttpServletRequest request) {
		return String.valueOf(request.getLocalPort());
	}

}
