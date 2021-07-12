package cn.jaminye.rabbitmqbase.pubsub;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订阅者1
 * @author jaminye
 * @date 2021/7/11 下午12:56
 */
public class SubscriberOne {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare("one队列",false,false,false,null);
        //将队列与交换机绑定
        channel.queueBind("one队列","发布订阅交换机","");
        //收消息
        channel.basicConsume("one队列",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息======>one=============>"+new String(body));
            }
        });
    }
}
