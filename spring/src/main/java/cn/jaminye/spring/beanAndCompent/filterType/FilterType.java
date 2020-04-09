package cn.jaminye.spring.beanAndCompent.filterType;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义过滤器
 *
 * @author Jamin
 * @date 2020/4/4 16:01
 */
public class FilterType implements TypeFilter {
  @Override
  public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
      throws IOException {
    //     获取类信息
    ClassMetadata classMetadata = metadataReader.getClassMetadata();
    System.out.println(classMetadata.getClassName());

    if (classMetadata.getClassName().contains("Dao")) {
      return true;
    }
    return false;
  }
}
