package cn.jaminye.rabbitmqbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"pass.properties"}, encoding = "gbk")
public class RabbitmqBaseApplication {


	public static void main(String[] args) {

		SpringApplication.run(RabbitmqBaseApplication.class, args);
	}


}
