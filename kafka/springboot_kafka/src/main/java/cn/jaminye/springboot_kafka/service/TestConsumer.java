package cn.jaminye.springboot_kafka.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TestConsumer {

	@Resource(name = "kafkaConsumer")
	KafkaConsumer kafkaConsumer;
	private static List<String> list = new ArrayList<>(200);
	private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

	/**
	 * list 最小批量插入大小
	 */
	private static final int MINI_BATCH_SIZE = 200;

	private long beginTime = System.currentTimeMillis();
	/**
	 * 插入数据库频率
	 */
	private static final long INTO_DATABASE_INTERVAL = 1000;

	@SneakyThrows
	public void consume() {
		//指定消费主题
		kafkaConsumer.subscribe(Collections.singletonList("test"));
		while (true) {
			//每200毫秒拉一下数据
			ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(200));
			for (ConsumerRecord<String, String> record : records) {
				String strRecord = record.value();
				System.out.println("==========================>"+record.value());
				log.info("===========topic========" + record.topic() + "========partition=======" + record.partition() + "=======offset=======" + record.offset());
				parseData(strRecord);
			}
			// 列表数据达到200条或者1秒钟入一次
			if (list.size() >= MINI_BATCH_SIZE || ((System.currentTimeMillis() - beginTime >= INTO_DATABASE_INTERVAL) && list.size() > 0)) {
				List<String> dataList = new ArrayList<>(list);
				list.clear();
				//提交
				kafkaConsumer.commitAsync();
				threadPoolExecutor.execute(() -> {
					//	do something dataList
					dataList.clear();
				});
				beginTime = System.currentTimeMillis();
			} else if (list.size() == 0) {
				// 当前topic数据量少暂停拉取
				Thread.sleep(500);
			}

		}
	}

	/**
	 * 解析数据
	 *
	 * @param strRecord 数据字符串
	 */
	private void parseData(String strRecord) {
		//		do something parse then add object
		list.add(strRecord);
	}
}
