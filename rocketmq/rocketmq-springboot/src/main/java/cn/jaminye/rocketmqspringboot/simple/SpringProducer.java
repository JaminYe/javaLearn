package cn.jaminye.rocketmqspringboot.simple;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/8/30 15:54
 */
@Component
public class SpringProducer {
	@Autowired
	RocketMQTemplate rocketMQTemplate;

	public void sendMessage(String topic, String msg) {
		Message<String> message = MessageBuilder.withPayload(msg).build();
		SendResult sendResult = rocketMQTemplate.syncSend(topic + ":" + "TAG1", message);
		System.out.println(sendResult);
	}

}
