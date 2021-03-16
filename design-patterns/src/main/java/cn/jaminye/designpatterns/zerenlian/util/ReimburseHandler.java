package cn.jaminye.designpatterns.zerenlian.util;

import cn.jaminye.designpatterns.zerenlian.pojo.Reimbursement;

/**
 * @author Jamin
 * @date 2021/3/16 16:08
 * 报销接口
 */
public interface ReimburseHandler {
	/**
	 * 报销
	 *
	 * @param reimbursement
	 * @return {@link boolean}
	 * @author Jamin
	 * @date 2021/3/16 16:10
	 */
	Boolean reminder(Reimbursement reimbursement);

}
