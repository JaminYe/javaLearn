package cn.jaminye.rabbitmqbase.workqueues;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列模式生产者
 *
 * @author jaminye
 * @date 2021/7/11 上午10:18
 */
public class workQueuesProduct {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitMqUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        /**
         * 声明一个队列
         * 队列名称,没有自动创建
         * 是否持久化
         * 是否独占,其他连接不能访问
         * 是否自动删除
         * 其他参数
         */
        channel.queueDeclare("工作队列", false, false, false, null);
        for (int i = 0; i < 99; i++) {
            String msg = "测试消息"+i;
            channel.basicPublish("", "工作队列", null, msg.getBytes());
        }
        System.out.println("=======发送完毕=======");
        channel.close();
        connection.close();
    }
}
