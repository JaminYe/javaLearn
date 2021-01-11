package cn.jaminye.spring.beanAndCompent.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 自定义判断注入
 *
 * @author Jamin
 * @date 2020/4/4 18:09
 */
public class CustomizeCondition implements Condition {
  @Override
  public boolean matches(
      ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
    if (conditionContext.getBeanFactory().containsBean("person")) {
      return true;
    }
    return false;
  }
}
