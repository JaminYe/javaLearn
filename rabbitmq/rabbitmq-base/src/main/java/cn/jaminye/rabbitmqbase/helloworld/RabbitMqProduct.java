package cn.jaminye.rabbitmqbase.helloworld;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 * @author jaminye
 * @date 2021/7/6 上午12:07
 */
public class RabbitMqProduct {

	public static void main(String[] args) throws IOException, TimeoutException {
		//获取连接
		Connection connection = RabbitMqUtil.getConnection();
		//获取通道
		Channel channel = connection.createChannel();
		/**
		 * 队列名称,没有自动创建
		 * 是否持久化
		 * 是否独占,其他连接不能访问
		 * 是否自动删除
		 * 其他参数
		 */
		channel.queueDeclare("测试队列", false, false, false, null);
		String msg = "测试消息";
		channel.basicPublish("", "测试队列", null, msg.getBytes());
		System.out.println("=======发送成功=======");
		channel.close();
		connection.close();
	}
}
