package cn.jaminye.rocketmqspringboot;

import cn.jaminye.rocketmqspringboot.simple.SpringProducer;
import cn.jaminye.rocketmqspringboot.transaction.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RocketmqSpringbootApplicationTests {

	@Resource
	SpringProducer springProducer;
	@Resource
	Producer producer;

	@Test
	void contextLoads() {
		springProducer.sendMessage("1", "2");
	}

	@Test
	void test2() throws InterruptedException {
		producer.sendMessage();
		Thread.sleep(100000);
	}

}
