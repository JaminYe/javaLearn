package cn.jaminye.scheduled.consumer;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
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
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pull-group");
		consumer.setNamesrvAddr("192.168.147.134:9876");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe("scheduled-topic", "*");
		//负载
		consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				msgs.stream().map(messageExt -> new String(messageExt.getBody())).forEach(System.out::println);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}


}
