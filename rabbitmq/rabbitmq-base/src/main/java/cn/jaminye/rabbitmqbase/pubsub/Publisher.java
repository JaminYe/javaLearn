package cn.jaminye.rabbitmqbase.pubsub;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布者
 *
 * @author jaminye
 * @date 2021/7/11 下午12:55
 */
public class Publisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        //创建一个交换机广播模式
        channel.exchangeDeclare("发布订阅交换机", BuiltinExchangeType.FANOUT);
        //发布到交换机
        channel.basicPublish("发布订阅交换机", "", null, "消息".getBytes());
        channel.close();
        connection.close();
        System.out.println("消息发送成功");
    }
}
