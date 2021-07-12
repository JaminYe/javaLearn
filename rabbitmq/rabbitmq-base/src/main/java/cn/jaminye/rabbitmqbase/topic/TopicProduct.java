package cn.jaminye.rabbitmqbase.topic;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author jaminye
 * @date 2021/7/11 下午7:33
 */
public class TopicProduct {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        //创建一个交换机topic模式
        // channel.exchangeDeclare("topic交换机", BuiltinExchangeType.TOPIC);
        HashMap<String, String> map = new HashMap<String, String>(12);
        map.put("china.anhui.hefei.weather","合肥的天气情况");
        map.put("china.anhui.wuhu.weather","芜湖的天气情况");
        map.put("china.jiangsu.nanjing.weather","南京的天气情况");
        map.put("china.jiangsu.suzhou.weather","苏州的天气情况");
        map.put("china.anhui.hefei.news","合肥的新闻情况");
        map.put("china.jiangsu.nanjing.news","南京的天气情况");
        map.entrySet().forEach(map1->{
            try {
                //发布到交换机    指定routingkey
                channel.basicPublish("topic交换机", map1.getKey(), null, map1.getValue().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        channel.close();
        connection.close();
        System.out.println("消息发送成功");
    }
}
