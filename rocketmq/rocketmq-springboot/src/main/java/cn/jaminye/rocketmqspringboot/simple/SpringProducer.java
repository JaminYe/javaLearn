package cn.jaminye.rocketmqspringboot.simple;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jamin
 * @date 2021/8/30 15:54
 */
@Component
public class SpringProducer {
	@Resource
	RocketMQTemplate mqTemplate;

	public void sendMessage() {
		Message<String> message = MessageBuilder.withPayload("12345").build();
		//topic:tag
		mqTemplate.syncSend("topic-1" + ":" + "TAG1", message, 100000);
		mqTemplate.syncSend("topic-1" + ":" + "TAG2", message, 100000);
	}

}
