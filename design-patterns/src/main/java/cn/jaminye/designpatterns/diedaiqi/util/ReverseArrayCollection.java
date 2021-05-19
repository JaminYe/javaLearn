package cn.jaminye.designpatterns.diedaiqi.util;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Jamin
 * @date 2021/3/17 9:14
 * 倒序的迭代器
 */
public class ReverseArrayCollection<T> implements Iterable {
	public T[] array;


	public ReverseArrayCollection(T... objects) {
		this.array = Arrays.copyOfRange(objects, 0, objects.length);
	}

	@Override
	public Iterator iterator() {
		return new ReverseIterator();
	}

	class ReverseIterator implements Iterator {
		//索引位置
		int index;

		public ReverseIterator() {
			this.index = array.length;
		}

		@Override
		public boolean hasNext() {
			return index > 0;
		}

		@Override
		public Object next() {
			index--;
			return array[index];
		}
	}
}
