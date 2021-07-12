package cn.jaminye.rabbitmqbase.route;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jaminye
 * @date 2021/7/11 下午2:04
 */
public class RouteConsumerOne {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare("路由模式队列1",false,false,false,null);
        //交换机队列绑定
        channel.queueBind("路由模式队列1","路由模式交换机","key1");
        //消费队列
        channel.basicConsume("路由模式队列1",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1=====>"+new String(body));
            }
        });
    }
}
