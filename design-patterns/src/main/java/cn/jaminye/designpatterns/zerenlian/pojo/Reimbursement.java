package cn.jaminye.designpatterns.zerenlian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 报销
 *
 * @author Jamin
 * @date 2021/3/16 16:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reimbursement {
	/**
	 * 人员姓名
	 */
	private String name;
	/**
	 * 金额
	 */
	private BigDecimal money;


}
