package cn.jaminye.filter.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamin
 * @date 2021/8/17 19:34
 */
public class Producer {
	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("filter-group");
		producer.setNamesrvAddr("192.168.147.134:9876");
		producer.start();
		Message message1 = new Message("filter-topic", "TAG1", "TAG1-1".getBytes());
		message1.putUserProperty("a", "1");
		Message message2 = new Message("filter-topic", "TAG1", "TAG1-2".getBytes());
		message2.putUserProperty("a", "2");
		Message message3 = new Message("filter-topic", "TAG2", "TAG2-1".getBytes());
		message3.putUserProperty("a1", "3");
		Message message4 = new Message("filter-topic", "TAG2", "TAG2-2".getBytes());
		message4.putUserProperty("a1", "3");
		Message message5 = new Message("filter-topic", "TAG3", "TAG3-1".getBytes());
		message5.putUserProperty("a2", "3");
		Message message6 = new Message("filter-topic", "TAG3", "TAG3-2".getBytes());
		message6.putUserProperty("a2", "3");
		List<Message> messages = new ArrayList<>(8);
		messages.add(message1);
		messages.add(message2);
		messages.add(message3);
		messages.add(message4);
		messages.add(message5);
		messages.add(message6);
		producer.send(messages);
		producer.shutdown();
	}
}
