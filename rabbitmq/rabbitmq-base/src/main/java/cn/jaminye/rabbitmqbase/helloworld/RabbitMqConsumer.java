package cn.jaminye.rabbitmqbase.helloworld;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author jaminye
 * @date 2021/7/11 上午9:42
 */
public class RabbitMqConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicConsume("测试队列", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息内容为==========>" + new String(body));
                System.out.println("==========id======>" + envelope.getDeliveryTag());
                //确认消息已经接受，消息id，是否确认以前的消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

}
