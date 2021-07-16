package cn.jaminye.rabbitmqbase.confirm;

import cn.jaminye.rabbitmqbase.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author Jamin
 * @date 2021/7/13 15:00
 */
public class ConfirmProduct {
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = RabbitMqUtil.getConnection();
		Channel channel = connection.createChannel();
		//创建一个交换机topic模式
		// channel.exchangeDeclare("topic交换机", BuiltinExchangeType.TOPIC);
		HashMap<String, String> map = new HashMap<String, String>(12);
		map.put("china.anhui.hefei.weather", "合肥的天气情况");
		map.put("china.anhui.wuhu.weather", "芜湖的天气情况");
		map.put("china.jiangsu.nanjing.weather", "南京的天气情况");
		map.put("china.jiangsu.suzhou.weather", "苏州的天气情况");
		map.put("china.anhui.hefei.news", "合肥的新闻情况");
		map.put("china.jiangsu.nanjing.news", "南京的新闻情况");
		//开启监听
		channel.confirmSelect();
		/**
		 * 发送到rabbitmq回调
		 */
		channel.addConfirmListener(new ConfirmListener() {
			/**
			 * 发送成功回调
			 * @param deliveryTag
			 * @param multiple
			 * @throws IOException
			 */
			@Override
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("发送成功的消息ID========>" + deliveryTag);
			}

			/**
			 *发送失败回调
			 * @param deliveryTag
			 * @param multiple
			 * @throws IOException
			 */
			@Override
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("发送失败的消息ID========>" + deliveryTag);
			}
		});
		/**
		 * 发送到队列失败回调
		 */
		channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
			System.err.println("===========未可达===========>" + new String(body));
		});
		map.entrySet().forEach(map1 -> {
			try {
				//发布到交换机    指定routingkey  mandatory指定为true
				channel.basicPublish("confirm交换机", map1.getKey(), true, null, map1.getValue().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		// channel.close();
		// connection.close();
		System.out.println("消息发送成功");
	}
}
