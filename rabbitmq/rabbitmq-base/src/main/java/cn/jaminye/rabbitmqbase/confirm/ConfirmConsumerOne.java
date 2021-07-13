package cn.jaminye.rabbitmqbase.confirm;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jaminye
 * @date 2021/7/12 下午2:56
 */
public class ConfirmConsumerOne {
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = RabbitMqUtil.getConnection();
		Channel channel = connection.createChannel();
		//创建队列
		channel.queueDeclare("confirmOne", false, false, false, null);
		//topic交换机 绑定交换机路由key
		channel.queueBind("confirmOne", "confirm交换机", "china.anhui.hefei.*");
		channel.basicConsume("confirmOne", new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("========消息========>" + new String(body));
			}
		});
	}
}
