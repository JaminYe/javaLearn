package cn.jaminye.designpatterns.observer.pojo;

import cn.jaminye.designpatterns.observer.util.ObServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jamin
 * @date 2021/3/17 10:24
 */
public class Subject {
	ExecutorService executorService = Executors.newFixedThreadPool(5);
	List<ObServer> obServers = new ArrayList<>();
	/**
	 * state
	 */
	private String state;

	public String getState() {

		return state;
	}

	public void setState(String state) {
		this.state = state;
		try {
			this.notifyOb();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addOb(ObServer obServer) {
		obServers.add(obServer);
	}

	public void notifyOb() throws InterruptedException {
		for (ObServer obServer : obServers) {
			Thread.sleep((long) new Random(System.currentTimeMillis()).nextFloat() * 1000);
			executorService.submit(() -> obServer.update());

		}
	}
}
