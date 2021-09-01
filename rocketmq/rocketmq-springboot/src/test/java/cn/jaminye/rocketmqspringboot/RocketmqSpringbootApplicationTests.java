package cn.jaminye.rocketmqspringboot;

import cn.jaminye.rocketmqspringboot.transaction.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RocketmqSpringbootApplicationTests {
	@Resource
	Producer producer;

	@Test
	void contextLoads() throws InterruptedException {
		producer.sendMessage();
		Thread.sleep(100 * 1000);
	}

}
