package cn.jaminye.spring.beanAndCompent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 人类实体类
 *
 * @author Jamin
 * @date 2020/4/4 13:42
 */
@Data
@AllArgsConstructor
public class Person {
  /** id */
  private Integer id;

  /** 姓名 */
  private String name;
  /** 年龄 */
  private int age;

  public Person() {
    System.out.println("触发构造方法");
  }
}
