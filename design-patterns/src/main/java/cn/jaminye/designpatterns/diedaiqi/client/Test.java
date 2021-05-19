package cn.jaminye.designpatterns.diedaiqi.client;

import cn.jaminye.designpatterns.diedaiqi.util.ReverseArrayCollection;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Jamin
 * @date 2021/3/17 9:24
 * 测试
 */
public class Test {
	public static void main(String[] args) {
		ReverseArrayCollection<? extends Serializable> reverseArrayCollection = new ReverseArrayCollection<>("1", 2, 3, "4", 5, "6", "7", 8);
		Iterator iterator = reverseArrayCollection.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
