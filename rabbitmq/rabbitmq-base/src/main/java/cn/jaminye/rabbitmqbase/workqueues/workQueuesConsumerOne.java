package cn.jaminye.rabbitmqbase.workqueues;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一号消费者
 * @author jaminye
 * @date 2021/7/11 上午10:22
 */
public class workQueuesConsumerOne {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        final Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare("工作队列", false, false, false, null);
        //同时只消费1个，知道消费者确认消息
        // channel.basicQos(1);
        channel.basicConsume("工作队列", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // try {
                //     Thread.sleep(200);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
                System.out.println("消息内容为==========>" + new String(body));
                //确认消息已经接受，消息id，是否确认以前的消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
