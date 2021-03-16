package cn.jaminye.designpatterns.zerenlian.handler;

import cn.jaminye.designpatterns.zerenlian.pojo.Reimbursement;
import cn.jaminye.designpatterns.zerenlian.util.ReimburseHandler;

import java.math.BigDecimal;

/**
 * 主任报销
 *
 * @author Jamin
 * @date 2021/3/16 16:20
 */
public class DirectorHandler implements ReimburseHandler {
	private static final int MAX_REIMBURSE_VALUE = 5000;

	/**
	 * 小于10000
	 *
	 * @param reimbursement
	 * @return {@link Boolean}
	 * @author Jamin
	 * @date 2021/3/16 16:20
	 */
	@Override
	public Boolean reminder(Reimbursement reimbursement) {
		//获取金额
		BigDecimal money = reimbursement.getMoney();
		//大于1000返回null
		if (money.compareTo(BigDecimal.valueOf(MAX_REIMBURSE_VALUE)) > 0) {
			return null;
		}
		return true;
	}
}
