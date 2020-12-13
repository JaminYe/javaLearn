package cn.jaminye.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/07 14:33:29
 */

public class AtomicIntegerArrayTest {



  public static void main(String[] args) {
    int[] array = new int[] {1, 2};
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
    atomicIntegerArray.getAndSet(0, 11);
    System.out.println(atomicIntegerArray.get(0));
    System.out.println(array[0]);

  }
}
