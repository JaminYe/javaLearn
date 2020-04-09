package cn.jaminye.spring.autowireandresource.entity;

import lombok.Data;

/**
 * @author Jamin
 * @date 2020/4/7 20:36
 */
@Data
public class Test {
  /** name */
  private String name;

  public Test() {
    System.out.println("触发test无参构造");
  }
}
