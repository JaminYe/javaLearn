package cn.jaminye.designpatterns.zerenlian.client;

import cn.jaminye.designpatterns.zerenlian.handler.CEOHandler;
import cn.jaminye.designpatterns.zerenlian.handler.DirectorHandler;
import cn.jaminye.designpatterns.zerenlian.handler.ManagerHandler;
import cn.jaminye.designpatterns.zerenlian.pojo.Reimbursement;
import cn.jaminye.designpatterns.zerenlian.util.ReimburseHandler;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamin
 * @date 2021/3/16 16:25
 */
@Slf4j
public class Test {
	public static void main(String[] args) {
		List<ReimburseHandler> handlers = new ArrayList<>();
		handlers.add(new ManagerHandler());
		handlers.add(new DirectorHandler());
		handlers.add(new CEOHandler());
		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements.add(new Reimbursement("1", BigDecimal.valueOf(999)));
		reimbursements.add(new Reimbursement("2", BigDecimal.valueOf(1000)));
		reimbursements.add(new Reimbursement("3", BigDecimal.valueOf(1001)));
		reimbursements.add(new Reimbursement("4", BigDecimal.valueOf(5001)));
		reimbursements.add(new Reimbursement("4", BigDecimal.valueOf(10000)));
		for (Reimbursement reimbursement : reimbursements) {
			for (ReimburseHandler handler : handlers) {
				Boolean flag = handler.reminder(reimbursement);
				if (null != flag) {
					log.info("=======>{}", handler.getClass().getSimpleName());
					break;
				}
			}
		}


	}
}
