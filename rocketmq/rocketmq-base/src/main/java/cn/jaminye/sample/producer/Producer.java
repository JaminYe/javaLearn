package cn.jaminye.sample.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author Jamin
 * @date 2021/8/15 9:36
 */

public class Producer {
	public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

		DefaultMQProducer product = new DefaultMQProducer("product");
		product.setNamesrvAddr("192.168.147.134:9876");
		product.start();

		/**
		 * 同步发送
		 */
		Message message = new Message("java-topic", "hello-world".getBytes());
		SendResult sendResult = product.send(message);
		/**
		 * 批量发送 topic必须相同  官方示例中小于1m 不能是延迟,事务消息
		 */
		/*Message message1 = new Message("batch-topic2", "hello-world1".getBytes());
		Message message2 = new Message("batch-topic2", "hello-world2".getBytes());
		Message message3 = new Message("batch-topic2", "hello-world3".getBytes());
		List<Message> messages = new ArrayList<>(8);
		messages.add(message1);
		messages.add(message2);
		messages.add(message3);
		SendResult sendResult = product.send(messages);*/
		/**
		 *异步发送
		 */
		/*CountDownLatch countDownLatch = new CountDownLatch(1);
		Message message = new Message("async-topic", "async-topic".getBytes());
		product.send(message, new SendCallback() {
			@Override
			public void onSuccess(SendResult sendResult) {
				countDownLatch.countDown();
			}

			@Override
			public void onException(Throwable throwable) {
				System.err.println(throwable.getMessage());
			}
		});
		countDownLatch.await();*/
		/**
		 * 单向发送
		 */
		//主题,标签,key,内容
		/*Message message = new Message("send-one-way", "tag1", "1", "send-one-way".getBytes());
		product.sendOneway(message);
		//这种方法无返回值,等待发送完成
		Thread.sleep(5000);*/

		// System.out.println("=================发送结果=============" + sendResult);
		product.shutdown();
	}
}
