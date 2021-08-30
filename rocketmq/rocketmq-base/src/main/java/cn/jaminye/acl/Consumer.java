package cn.jaminye.acl;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author Jamin
 * @date 2021/8/30 11:54
 */
public class Consumer {
	public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		DefaultMQProducer product = new DefaultMQProducer("product2", new AclClientRPCHook(new SessionCredentials("RocketMQ", "12345678")));
		product.setNamesrvAddr("192.168.147.134:9876");
		product.start();
		Message message = new Message("java-topic", "hello-world".getBytes());
		SendResult sendResult = product.send(message);
		System.out.println(sendResult);
		product.shutdown();
	}
}
