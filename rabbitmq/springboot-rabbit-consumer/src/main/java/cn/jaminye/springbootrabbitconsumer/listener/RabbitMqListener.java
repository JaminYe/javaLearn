package cn.jaminye.springbootrabbitconsumer.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/7/18 10:10
 */
@Component
public class RabbitMqListener {
	/**
	 * 监听队列
	 *
	 * @param message 消息
	 * @author Jamin
	 * @date 2021/7/18 10:56
	 */
	@RabbitListener(queues = "springboot-queue")
	public void listener(Message message) {
		System.out.println(message);
	}
}
