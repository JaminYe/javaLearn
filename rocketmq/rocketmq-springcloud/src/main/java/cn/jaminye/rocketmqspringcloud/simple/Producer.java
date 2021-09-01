package cn.jaminye.rocketmqspringcloud.simple;

import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamin
 * @date 2021/8/31 15:10
 */
@Component
public class Producer {
	@Resource
	Source source;

	public void sendMessage() {
		Map<String, Object> headers = new HashMap<>(8);
		headers.put(MessageConst.PROPERTY_TAGS, "testTag");
		MessageHeaders messageHeaders = new MessageHeaders(headers);
		Message<String> message = MessageBuilder.createMessage("测试消息", messageHeaders);
		this.source.output().send(message);

	}
}
