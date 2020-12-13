package cn.jaminye.kafkademo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * 消费者
 *
 * @author jaminye
 * @date 2020/12/13 下午7:30
 */
public class MsgConsumer {


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.211.55.4:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
        //自动提交offset时间间隔
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //关闭自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        /*消费者会一直向协调者broker发送心跳数据，当协调者发现消费者不发送心跳了，并且时间超过了设置的时间，就认为该消费者挂了，通知同组下
        其他消费者进行rebalance操作*/
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);
        //broker多久感知不到消费者认为其挂了
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 1000 * 10);
        //两次poll时间都超过此时间，会认为其挂了
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 30 * 1000);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<Object, Object> consumer = new KafkaConsumer<>(properties);
        String topicName = "order-topic";
        //消费主题
        // consumer.subscribe(Arrays.asList(topicName));
        //指定分区消费
        consumer.assign(Arrays.asList(new TopicPartition(topicName, 0)));
        //从头开始
        consumer.seekToBeginning(Arrays.asList(new TopicPartition(topicName, 0)));
        //指定偏移量消费
        consumer.seek(new TopicPartition(topicName, 0), 10);
        while (true) {
            //长轮询
            ConsumerRecords<Object, Object> consumerRecords = consumer.poll(Duration.ofSeconds(Integer.MAX_VALUE));
            consumerRecords.forEach(objectObjectConsumerRecord -> {
                System.out.println(objectObjectConsumerRecord.offset() + "======================"
                        + objectObjectConsumerRecord.key() + "---------------" + objectObjectConsumerRecord.value());
            });
            if (consumerRecords.count()>0){
                consumer.commitSync();
            }
        }
    }


}
