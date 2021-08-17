package cn.jaminye.order.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Jamin
 * @date 2021/8/16 10:36
 */
public class Consumer {
	public static void main(String[] args) throws Exception {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order-consumer");
		//消费组订阅的消息未过期从头开始,已过期从当前开始
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe("order-topic", "*");
		consumer.setNamesrvAddr("192.168.147.134:9876");
		//顺序取
		consumer.registerMessageListener(new MessageListenerOrderly() {
			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
				list.stream().map(messageExt -> new String(messageExt.getBody())).forEach(System.out::println);
				return ConsumeOrderlyStatus.SUCCESS;
			}
		});
		/*consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
				list.stream().map(messageExt -> new String(messageExt.getBody())).forEach(System.out::println);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});*/
		consumer.start();
	}
}
