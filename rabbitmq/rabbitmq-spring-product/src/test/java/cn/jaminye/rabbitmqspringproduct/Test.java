package cn.jaminye.rabbitmqspringproduct;

/**
 * @author Jamin
 * @date 2021/7/18 11:43
 */

import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.ImportResource;
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
		rabbitTemplate.convertAndSend("confirm_exchange", "key", "消息可靠性质");
		Thread.sleep(10000L);
	}

	@org.junit.Test
	public void testAck() throws InterruptedException {
		rabbitTemplate.convertAndSend("ack_exchange", "key", "消息者确认");
	}

	@org.junit.Test
	public void testTtl() throws InterruptedException {
		rabbitTemplate.convertAndSend("ttl_exchange","ttl.1","队列自动删除");
		rabbitTemplate.convertAndSend("ttl_exchange","ttl.1",message ->{
			//单个消息设置过期时间      到时间不会被删除在消费时判断过期不会返回会删除
			message.getMessageProperties().setExpiration("10000");
			return message;
		} );
	}

}
