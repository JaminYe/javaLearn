package cn.jaminye;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author jaminye
 * @date 2021/7/17 上午11:36
 */
public class SpringQueueListener1 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println(this.getClass().getName() + "===========" + new String(message.getBody()));
    }
}
