package cn.jaminye.scheduled.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author Jamin
 * @date 2021/8/17 19:34
 */
public class Producer {
	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("scheduled-group");
		producer.setNamesrvAddr("192.168.147.134:9876");
		producer.start();
		Message message1 = new Message("scheduled-topic", "scheduled".getBytes());
		//1-18 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
		message1.setDelayTimeLevel(3);
		producer.send(message1);
		Message message = new Message("scheduled-topic", "正常".getBytes());
		producer.send(message);
		producer.shutdown();
	}
}
