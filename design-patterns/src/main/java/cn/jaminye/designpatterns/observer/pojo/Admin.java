package cn.jaminye.designpatterns.observer.pojo;

import cn.jaminye.designpatterns.observer.util.ObServer;

/**
 * @author Jamin
 * @date 2021/3/17 10:16
 */
public class Admin extends ObServer {

	@Override
	public void update() {
		System.out.println("admin接受到修改");
	}
}
