package cn.jaminye.springbootrabbitmqproduct;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqProductApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Test
	void contextLoads() throws InterruptedException {

		/*rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			if (ack) {
				System.out.println("消息到达");
			} else {
				System.out.println("消息未到达======原因:==>" + cause + "=====message=====");
			}
		});
		//是否开启retuen
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setReturnsCallback(returnedMessage -> {
			//消息内荣
			System.out.println(new String(returnedMessage.getMessage().getBody()));
			//回退原因
			System.out.println(returnedMessage.getReplyText());
		});
		rabbitTemplate.convertAndSend("springboot-exchange", "springboot.1.12211212", "springboot消息");
		Thread.sleep(10000);*/
		for (int i = 0; i < 20; i++) {
			rabbitTemplate.convertAndSend("test_exchange_dlx0", "test.dlx.1", "死信队列");
		}
	}
}
