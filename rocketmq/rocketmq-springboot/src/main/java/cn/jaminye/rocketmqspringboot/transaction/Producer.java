package cn.jaminye.rocketmqspringboot.transaction;

import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jamin
 * @date 2021/8/30 17:08
 */
@Component
public class Producer {
	@Resource
	RocketMQTemplate rocketMQTemplate;

	public void sendMessage() {
		Message<String> message1 =
				MessageBuilder.withPayload("123").setHeader(RocketMQHeaders.TRANSACTION_ID, "1").setHeader(RocketMQHeaders.TOPIC, "123")
						.setHeader(RocketMQHeaders.TAGS, "1231").setHeader("a", 1).build();
		TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction("springboot-producer1:TAG1", message1, null);
		System.out.println(transactionSendResult);
	}
}
