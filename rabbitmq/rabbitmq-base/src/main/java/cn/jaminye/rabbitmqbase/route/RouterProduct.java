package cn.jaminye.rabbitmqbase.route;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

/**
 * 路由模式
 * @author jaminye
 * @date 2021/7/11 下午1:57
 */
public class RouterProduct {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        //指定direct模式
        channel.exchangeDeclare("路由模式交换机",BuiltinExchangeType.DIRECT);
        channel.basicPublish("路由模式交换机","key1",null,"key1消息1".getBytes());
        channel.basicPublish("路由模式交换机","key1",null,"key1消息2".getBytes());
        channel.basicPublish("路由模式交换机","key2",null,"key2消息".getBytes());
        channel.close();
        connection.close();

    }
}
