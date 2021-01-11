package cn.jaminye.spring.beanAndCompent.importSelector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 通过importSelector接口往容器导入注解
 *
 * @author Jamin
 * @date 2020/4/5 10:45
 */
public class ImportSelectorImpl implements ImportSelector {
  @Override
  public String[] selectImports(AnnotationMetadata annotationMetadata) {
    return new String[] {"cn.jaminye.spring.entity.Dog"};
  }
}
