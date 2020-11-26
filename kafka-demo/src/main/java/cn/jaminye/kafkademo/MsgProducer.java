package cn.jaminye.kafkademo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * @author Jamin
 * @date 2020/11/25 10:23
 * 生产者
 */
public class MsgProducer {
	KafkaProducer<String, String> producer;

	@Before
	public void init() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.150.120:9092,192.168.150.120:9093,192.168.150.120:9094");
		//0 不需要回复,1等待leader写入log 不需要follower -1 等待集群全部写入log
		properties.put(ProducerConfig.ACKS_CONFIG, "1");
		//失败重试时间
		properties.put(ProducerConfig.RETRIES_CONFIG, "3");
		//本地缓存区时间
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
		//批量发送大小,批量满16k就发送
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
		//100毫秒批量未填充完成也发送
		properties.put(ProducerConfig.LINGER_MS_CONFIG, "100");
		//发送key从字符串转字节数组
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		producer = new KafkaProducer<>(properties);
	}

	@Test
	public void sendMsg() {
		for (int i = 0; i < 10; i++) {
			producer.send(new ProducerRecord<String, String>("test-topic", "test-key" + i, "key-value" + i));
		}
		producer.close();

	}

}
