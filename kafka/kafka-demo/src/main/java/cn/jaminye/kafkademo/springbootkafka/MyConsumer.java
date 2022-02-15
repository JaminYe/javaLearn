// package cn.jaminye.kafkademo.springbootkafka;
//
// import org.apache.kafka.clients.consumer.ConsumerRecord;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Component;
//
// /**
//  * @author jaminye
//  * @date 2020/12/13 下午8:50
//  */
// @Component
// public class MyConsumer {
//
//     @KafkaListener(topics = "mytopic", groupId = "testGroup")
//     public void listen(ConsumerRecord<String, String> record) {
//         String value = record.value();
//         String key = record.key();
//         System.out.println("key=======" + key + "value========" + value);
//     }
//
// }
