package cn.jaminye.rabbitmqbase.route;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jaminye
 * @date 2021/7/11 下午2:04
 */
public class RouteConsumerTwo {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("路由模式队列2",false,false,false,null);
        channel.queueBind("路由模式队列2","路由模式交换机","key2");
        channel.basicConsume("路由模式队列2",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2=====>"+new String(body));
            }
        });
    }
}
