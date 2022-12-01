package cn.jaminye.springboot_kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MsgConsumerConfig {

	@Bean(name = "kafkaConsumer")
	public KafkaConsumer getKafkaConsumer() {
		Map<String, Object> configMap = new HashMap<>(8);
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "124.221.220.44:9092");
		// 指定消费组
		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "consumeGroup-test1");
		//关闭自动提交
		configMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		configMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
		// 指定从最早的开始消费
		configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		//指定序列化类
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		return new KafkaConsumer(configMap);
	}

}
