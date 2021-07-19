package cn.jaminye;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/7/19 14:46
 */
@Component
public class AckListener implements ChannelAwareMessageListener {
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			System.out.println(new String(message.getBody()));
			/*业务处理*/
			Thread.sleep(6000L);
			//确认消息    是否签收前面的消息
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception ex) {
			//单个消息拒签          消息id 是否重新入队列             从获取时间到拒签时间不能太长,太长拒签不了
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
			//支持多个消息拒签          消息id  是否拒签前面的消息     是否重新入队列
			// channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
		}
	}
}
