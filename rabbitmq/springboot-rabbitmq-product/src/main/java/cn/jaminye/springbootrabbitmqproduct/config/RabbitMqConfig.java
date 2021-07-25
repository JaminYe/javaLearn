package cn.jaminye.springbootrabbitmqproduct.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public Binding binding(@Qualifier("queue") Queue queue, @Qualifier("exchange") Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("springboot.*").noargs();
	}

	/**
	 * 正常的队列
	 *
	 * @return
	 */
	@Bean
	public Queue testQueueDlx() {
		//设置死信交换机 过期时间   最大长度
		return QueueBuilder.durable("test_queue_dlx0").deadLetterExchange("dlx_exchange0").ttl(10000).maxLength(10).build();
	}

	/**
	 * 正常的交换机
	 *
	 * @return
	 */
	@Bean
	public Exchange testExchangeDlx() {
		return ExchangeBuilder.topicExchange("test_exchange_dlx0").build();
	}

	/**
	 * 正常队列交换机绑定
	 *
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding testQueueDlxBinding(@Qualifier("testQueueDlx") Queue queue, @Qualifier("testExchangeDlx") Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("test.dlx.#").noargs();
	}

	/**
	 * 死信队列
	 *
	 * @return
	 */
	@Bean
	public Queue dlxQueue() {
		return QueueBuilder.durable("dlx_queue0").build();
	}

	/**
	 * 死信交换机
	 *
	 * @return
	 */
	@Bean
	public FanoutExchange dlxExchange() {
		return ExchangeBuilder.fanoutExchange("dlx_exchange0").build();
	}

	/**
	 * 死信交换机队列绑定
	 *
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingDlx(@Qualifier("dlxQueue") Queue queue, @Qualifier("dlxExchange") FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}


}

