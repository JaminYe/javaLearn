package cn.jaminye.rabbitmqbase.helloworld;

import cn.jaminye.rabbitmqbase.util.PropertiesUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 * @author jaminye
 * @date 2021/7/6 上午12:07
 */
@Component
public class Product {


	public static void main(String[] args) throws IOException, TimeoutException {

		Properties properties = PropertiesUtil.getProperties("pass.properties");
		String property = properties.getProperty("rabbitmq.host");
		System.out.println(property);

/*		ConnectionFactory connectionFactory = new ConnectionFactory();
		//基础信息
		connectionFactory.setHost("10.211.55.7");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("jamin");
		connectionFactory.setPassword("123456");
		connectionFactory.setVirtualHost("test");
		//创建连接
		Connection connection = connectionFactory.newConnection();
		//获取通道
		Channel channel = connection.createChannel();
		// channel.queueDeclare("")*/
	}
}
