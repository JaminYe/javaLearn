package cn.jaminye.spring.profile.entity;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2020/4/5 17:34
 */
@Component
@Profile("test")
public class Person {

  /** id */
  private int id;

  public Person() {}

  public Person(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Person{" + "id=" + id + '}';
  }
}
