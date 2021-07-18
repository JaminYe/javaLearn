package cn.jaminye.rabbitmqspringproduct;

/**
 * @author Jamin
 * @date 2021/7/18 11:43
 */

import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class Test {
	@Resource
	RabbitTemplate rabbitTemplate;

	@org.junit.Test
	public void testConfirm() throws InterruptedException {
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
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
		rabbitTemplate.convertAndSend("confirm_exchange", "key1", "消息可靠性质");
		Thread.sleep(10000L);
	}
}
