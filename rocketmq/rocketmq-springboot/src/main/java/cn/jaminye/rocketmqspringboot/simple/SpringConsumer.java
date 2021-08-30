package cn.jaminye.rocketmqspringboot.simple;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/8/30 16:47
 */
@Component
@RocketMQMessageListener(topic = "1", consumerGroup = "springboot-group", selectorType = SelectorType.TAG, selectorExpression = "TAG1")
public class SpringConsumer implements RocketMQListener<String> {
	@Override
	public void onMessage(String s) {
		System.out.println(s);
	}
}
