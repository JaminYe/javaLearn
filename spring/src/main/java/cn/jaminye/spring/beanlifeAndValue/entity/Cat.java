package cn.jaminye.spring.beanlifeAndValue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2020/4/5 16:52
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
  /** id */
  @Value("12")
  private int id;

  /** name */
  @Value("${cat.name}")
  private String name;

  /** age */
  @Value("#{5}")
  private int age;
}
