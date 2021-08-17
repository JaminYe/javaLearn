package cn.jaminye.order.product;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Jamin
 * @date 2021/8/16 10:24
 */
public class Product {
	public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
		DefaultMQProducer producer = new DefaultMQProducer("order-group");
		producer.setNamesrvAddr("192.168.147.134:9876");
		producer.start();
		String[] strings = {"下单", "付款", "生成订单"};
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 3; j++) {
				String s = "订单__" + i + "___" + strings[j];
				Message message = new Message("order-topic", s.getBytes(RemotingHelper.DEFAULT_CHARSET));
				//根据id取模入队列使分类消息进一个队列
				producer.send(message, new MessageQueueSelector() {
					@Override
					public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
						int index = ((Integer) o) % list.size();
						return list.get(index);
					}
				}, i);
			}
		}
		producer.shutdown();
	}
}
