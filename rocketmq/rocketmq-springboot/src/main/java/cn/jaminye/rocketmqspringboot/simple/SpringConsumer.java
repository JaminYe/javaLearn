package cn.jaminye.rocketmqspringboot.simple;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/8/30 16:47
 */
@Component
// selectorType 过滤使用tag还是sql selectorExpression tag或者sql consumeMode顺序还是正常的 messageModel广播还是集群
@RocketMQMessageListener(topic = "topic-1", consumerGroup = "springboot-group", selectorType = SelectorType.SQL92, selectorExpression = "TAGS='TAG1'", consumeMode = ConsumeMode.CONCURRENTLY,
		messageModel = MessageModel.CLUSTERING)
public class SpringConsumer implements RocketMQListener<String> {
	@Override
	public void onMessage(String s) {
		System.out.println(s);
	}
}