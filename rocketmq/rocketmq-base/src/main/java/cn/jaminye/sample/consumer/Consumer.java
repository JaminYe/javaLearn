package cn.jaminye.sample.consumer;

/**
 * 拉
 *
 * @author Jamin
 * @date 2021/8/15 14:48
 */
public class Consumer {
	/**
	 * 拉模式
	 *
	 * @param args
	 * @author Jamin
	 * @date 2021/8/16 10:13
	 */
	// public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
	// 	/**
	// 	 * 拉模式,已弃用方式
	// 	 */
	// 	/*DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("pull-group");
	// 	consumer.setNamesrvAddr("192.168.147.134:9876");
	// 	consumer.start();
	// 	MessageQueue messageQueue = new MessageQueue();
	// 	messageQueue.setQueueId(2);
	// 	messageQueue.setBrokerName("broker-a");
	// 	messageQueue.setTopic("java-topic");
	// 	PullResult pullResult = consumer.pullBlockIfNotFound(messageQueue, null, 0, 2);
	// 	pullResult.getMsgFoundList().forEach(System.out::println);
	// 	consumer.shutdown();*/
	// 	/**
	// 	 * 现用
	// 	 */
	// 	DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("pull-group");
	// 	consumer.setNamesrvAddr("192.168.147.134:9876");
	// 	consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
	// 	consumer.subscribe("java-topic", "*");
	// 	consumer.start();
	// 	List<MessageExt> messageExtList = consumer.poll();
	// 	messageExtList.forEach(System.out::println);
	// 	consumer.shutdown();
	// }

	/**
	 * 推模式
	 *
	 * @param args
	 * @author Jamin
	 * @date 2021/8/16 10:13
	 */
	/*public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pull-group");
		consumer.setNamesrvAddr("192.168.147.134:9876");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe("java-topic", "*");
		//负载
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
