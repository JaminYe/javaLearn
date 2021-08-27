package cn.jaminye.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事务消息
 *
 * @author Jamin
 * @date 2021/8/26 19:35
 */
public class Producer {
	public static void main(String[] args) throws MQClientException, InterruptedException {
//组名不能与其他组名相同
		TransactionMQProducer transactionGroupProducer = new TransactionMQProducer("transactionGroup");
		ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
		transactionGroupProducer.setExecutorService(executorService);
		transactionGroupProducer.setNamesrvAddr("192.168.147.134:9876");
		TransactionListenerImpl transactionListener = new TransactionListenerImpl();
		transactionGroupProducer.setTransactionListener(transactionListener);
		transactionGroupProducer.start();
		for (int i = 0; i < 10; i++) {
			Message message = new Message("transaction-topic", String.valueOf(i).getBytes());
			message.putUserProperty("name", String.valueOf(i));
			TransactionSendResult transactionSendResult = transactionGroupProducer.sendMessageInTransaction(message, null);
			System.out.println(transactionSendResult.getSendStatus());
		}
	}
}
