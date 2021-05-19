package cn.jaminye.designpatterns.zerenlian.handler;

import cn.jaminye.designpatterns.zerenlian.pojo.Reimbursement;
import cn.jaminye.designpatterns.zerenlian.util.ReimburseHandler;

/**
 * @author Jamin
 * @date 2021/3/16 16:22
 * ceo报销
 */
public class CEOHandler implements ReimburseHandler {
	/**
	 * 任意金额
	 *
	 * @param reimbursement
	 * @return {@link java.lang.Boolean}
	 * @author Jamin
	 * @date 2021/3/16 16:39
	 */
	@Override
	public Boolean reminder(Reimbursement reimbursement) {
		return true;
	}
}
