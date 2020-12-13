package cn.jaminye.spring.autowireandresource.service;

import cn.jaminye.spring.autowireandresource.entity.Test;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Jamin
 * @date 2020/4/7 20:53
 */
@Service
public class TestService {
  //  @Qualifier("test10")
  @Inject private Test test1;

  @Override
  public String toString() {
    return "TestService{" + "test=" + test1 + '}';
  }
}
