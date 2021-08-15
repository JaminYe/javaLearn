package cn.jaminye.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.HashMap;
import java.util.Map;

/**
 * 拉
 *
 * @author Jamin
 * @date 2021/8/15 14:48
 */
public class Consumer {
	private static final Map<MessageQueue, Long> OFFSE_TABLE = new HashMap<MessageQueue, Long>();

	public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("pull-group");
		// DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("pull-group");
		consumer.setNamesrvAddr("192.168.147.134:9876");
		consumer.start();
		MessageQueue messageQueue = new MessageQueue();
		messageQueue.setQueueId(2);
		messageQueue.setBrokerName("broker-a");
		messageQueue.setTopic("java-topic");
		PullResult pullResult = consumer.pullBlockIfNotFound(messageQueue, null, 0, 2);
		pullResult.getMsgFoundList().forEach(System.out::println);
		consumer.shutdown();
	}

	/*public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pull-group");
		consumer.setNamesrvAddr("192.168.147.134:9876");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe("java-topic", "*");
		consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
				System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), list);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();

	}*/
}
