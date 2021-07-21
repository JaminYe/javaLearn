package cn.jaminye.springbootrabbitmqproduct.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabiit配置交换机队列
 *
 * @author Jamin
 * @date 2021/7/18 9:31
 */
@Configuration
public class RabbitMqConfig {

	/**
	 * 声明交换机
	 *
	 * @return
	 */
	@Bean
	public Exchange exchange() {
		return ExchangeBuilder.topicExchange("springboot-exchange").build();
	}

	/**
	 * 声明队列
	 *
	 * @return
	 */
	@Bean
	public Queue queue() {
		return QueueBuilder.durable("springboot-queue").build();
	}

	/**
	 * 绑定队列与交换机
	 *
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding binding(@Autowired Queue queue, @Autowired Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("springboot.*").noargs();
	}
}

