package cn.jaminye.concurrency.utils;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * @description:
 * @author: Jamin
 * @date: 2020/03/07 15:04:41
 */

public class UnsafeInstance {
  public static Unsafe reflectGetUnsafe() {
    try {
      Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");

      declaredField.setAccessible(true);

      return (Unsafe) declaredField.get(null);
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( NoSuchFieldException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
}
