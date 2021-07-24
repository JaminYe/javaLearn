package cn.jaminye.rabbitmqspringproduct;

/**
 * @author Jamin
 * @date 2021/7/18 11:43
 */

import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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
		rabbitTemplate.convertAndSend("confirm_exchange", "key", "消息可靠性质");
		Thread.sleep(10000L);
	}

	@org.junit.Test
	public void testAck() throws InterruptedException {
		rabbitTemplate.convertAndSend("ack_exchange", "key", "消息者确认");
	}

	@org.junit.Test
	public void testTtl() throws InterruptedException {
		rabbitTemplate.convertAndSend("ttl_queue", "队列自动删除");
		MessageProperties messageProperties = new MessageProperties();
		//设置消息过期时间2秒
        /*
        Expiration字段以微秒为单位表示TTL值,且与x-message-ttl具有相同的约束条件.因为Expiration字段必须是
        字符串类型,borker将只会接收以字符串形式表达的数字
        当同时指定了queue和message中的TTL值,两者中较小的值将会起作用
         */
		messageProperties.setExpiration("10000");
		Message message = new Message("测试过期消息,2秒内不消费将被删除".getBytes(), messageProperties);
		rabbitTemplate.convertAndSend("ttl_queue", message);
	}
}
