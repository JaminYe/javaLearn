package cn.jaminye.designpatterns.observer.client;

import cn.jaminye.designpatterns.observer.pojo.Admin;
import cn.jaminye.designpatterns.observer.pojo.Subject;
import cn.jaminye.designpatterns.observer.pojo.User;

/**
 * @author Jamin
 * @date 2021/3/17 10:36
 */
public class Test1 {
	public static void main(String[] args) {
		Subject subject = new Subject();
		//添加订阅者
		subject.addOb(new Admin());
		subject.addOb(new User());
		//修改内容
		subject.setState("1");
	}
}
