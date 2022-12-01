package cn.jaminye.springboot_kafka;

import cn.jaminye.springboot_kafka.service.TestConsumer;
import cn.jaminye.springboot_kafka.util.SpringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaApplication.class, args);
		TestConsumer testConsumer = SpringUtils.getBean(TestConsumer.class);
		testConsumer.consume();
	}


}
