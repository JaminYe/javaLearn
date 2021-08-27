package cn.jaminye.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 监听器
 *
 * @author Jamin
 * @date 2021/8/26 19:43
 */
public class TransactionListenerImpl implements TransactionListener {
	@Override
	public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {

		try {
			//开启事务
			//	do something
			if ("1".equals(msg.getProperty("name"))) {
				System.out.println("unknow");
				return LocalTransactionState.UNKNOW;
			}
			System.out.println("success");
			return LocalTransactionState.COMMIT_MESSAGE;
		} catch (Exception ex) {
			System.out.println("回滚事务");
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}


	}

	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt msg) {
		System.out.println("进入check");
		//	do something query db
		return true ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.UNKNOW;

	}
}
