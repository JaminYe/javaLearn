package cn.jaminye.rabbitmqbase.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * @author Jamin
 * @date 2021/7/7 17:13
 */
public class RabbitMqUtil {
	/**
	 * 读取配置文件获取连接
	 *
	 * @param
	 * @return {@link com.rabbitmq.client.Connection}
	 * @author Jamin
	 * @date 2021/7/7 17:14
	 */
	public static Connection getConnection() throws IOException, TimeoutException {
		//加载配置文件
		Properties properties = PropertiesUtil.getProperties("config.properties");
		ConnectionFactory connectionFactory = new ConnectionFactory();
		//基础信息
		connectionFactory.setHost(properties.getProperty("rabbitmq.host"));
		connectionFactory.setPort(Integer.parseInt(properties.getProperty("rabbitmq.port")));
		connectionFactory.setUsername(properties.getProperty("rabbitmq.userName"));
		connectionFactory.setPassword(properties.getProperty("rabbitmq.passwd"));
		connectionFactory.setVirtualHost(properties.getProperty("rabbitmq.vhost"));
		//创建连接
		Connection connection = connectionFactory.newConnection();
		return connection;
	}
}
