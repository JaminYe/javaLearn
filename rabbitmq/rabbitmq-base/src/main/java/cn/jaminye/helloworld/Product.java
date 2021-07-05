package cn.jaminye.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import sun.jvm.hotspot.debugger.win32.coff.COFFLineNumber;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 * @author jaminye
 * @date 2021/7/6 上午12:07
 */
public class Product {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

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
channel.queueDeclare("")
    }
}
