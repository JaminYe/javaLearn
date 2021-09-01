package cn.jaminye.rocketmqspringcloud.simple;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/8/31 15:28
 */
@Component
public class Consumer {

	@StreamListener(Sink.INPUT)
	public void onMessage(String message) {
		System.out.println(message);
	}
}
