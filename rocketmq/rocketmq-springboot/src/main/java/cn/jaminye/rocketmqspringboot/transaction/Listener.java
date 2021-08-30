package cn.jaminye.rocketmqspringboot.transaction;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 * @author Jamin
 * @date 2021/8/30 17:11
 */
@RocketMQTransactionListener
public class Listener implements RocketMQLocalTransactionListener {
	@Override
	public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
		System.out.println("message===============" + message);
		// 获取时添加前缀RocketMQHeaders.PREFIX
		String tags = message.getHeaders().get(RocketMQHeaders.PREFIX + RocketMQHeaders.TAGS, String.class);
		System.out.println("id==================" + tags);
		System.out.println("UNKNOWN==================");
		return RocketMQLocalTransactionState.UNKNOWN;
	}

	@Override
	public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
		System.out.println("message===============" + message.getPayload());
		return RocketMQLocalTransactionState.COMMIT;
	}
}
