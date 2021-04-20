package cn.jaminye.designpatterns.observer.pojo;

import cn.jaminye.designpatterns.observer.util.ObServer;

/**
 * @author Jamin
 * @date 2021/3/17 10:23
 */
public class User extends ObServer {
	@Override
	public void update() {
		System.out.println("user接收到修改");
	}
}
