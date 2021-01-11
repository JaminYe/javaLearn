package cn.jaminye.concurrency.atomic;

import cn.jaminye.concurrency.utils.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/07 15:02:39
 */

public class AtomicStudentUpdate {

  private String name;
  private volatile int age;
  private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
  private static long valueOffest;
  static {
    try {
      valueOffest = unsafe.objectFieldOffset(AtomicStudentUpdate.class.getDeclaredField("age"));
      System.out.println("偏移值" + valueOffest);
    } catch (NoSuchFieldException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



  public void compareAndUpdateAge(int old, int target) {
    unsafe.compareAndSwapInt(this, valueOffest, old, target);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }



  public AtomicStudentUpdate(String name, int age) {
    super();
    this.name = name;
    this.age = age;
  }

  public static void main(String[] args) {
    AtomicStudentUpdate atomicStudentUpdate = new AtomicStudentUpdate("叶明", 23);
    atomicStudentUpdate.compareAndUpdateAge(atomicStudentUpdate.getAge(), 24);
    System.out.println(atomicStudentUpdate.getAge());
  }
}
