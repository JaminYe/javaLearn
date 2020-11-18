package cn.jaminye.zklock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @author Jamin
 * @date 2020/11/18 16:35
 */
@Data
@AllArgsConstructor
public class Lock implements java.util.concurrent.locks.Lock {
	/**
	 * id
	 */
	private String lockId;
	/**
	 * 节点地址
	 */
	private String nodePath;

	/**
	 * 是否启动
	 */
	private boolean active;

	public Lock(String lockId, String nodePath) {
		this.active = false;
		this.lockId = lockId;
		this.nodePath = nodePath;

	}

	public boolean isActive() {
		return this.active;
	}


	@Override
	public void lock() {

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {

	}

	@Override
	public Condition newCondition() {
		return null;
	}
}
