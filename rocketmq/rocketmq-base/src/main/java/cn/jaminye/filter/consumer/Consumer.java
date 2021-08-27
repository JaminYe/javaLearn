package cn.jaminye.filter.consumer;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {

	/**
	 * 推模式
	 *
	 * @param args
	 * @author Jamin
	 * @date 2021/8/16 10:13
	 */
	public static void main(String[] args) throws MQClientException, InterruptedException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filter-group");
		consumer.setNamesrvAddr("192.168.147.134:9876");
		// consumer.subscribe("filter-topic", "TAG1 || TAG2");
		consumer.subscribe("filter-topic", MessageSelector.bySql("TAGS IN ('TAG1','TAG2') AND a between 0 and 1 "));
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				msgs.stream().map(messageExt -> new String(messageExt.getBody())).forEach(System.out::println);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
	}


}
