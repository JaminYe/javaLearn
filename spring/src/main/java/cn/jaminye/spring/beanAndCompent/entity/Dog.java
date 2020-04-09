package cn.jaminye.spring.beanAndCompent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 狗实体类
 *
 * @author Jamin
 * @date 2020/4/4 18:06
 */
@AllArgsConstructor
@Data
public class Dog {
  /** id */
  private int id;

  public Dog() {
    //    System.out.println("dog无参构造");
  }
}
