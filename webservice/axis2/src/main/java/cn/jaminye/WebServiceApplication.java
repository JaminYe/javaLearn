package cn.jaminye;

import com.alibaba.fastjson.JSON;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceGroupContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamin
 * @date 2021/1/27 9:57
 */
public class WebServiceApplication {
	Logger logger = LoggerFactory.getLogger(WebServiceApplication.class);

	/**
	 * 登陆保存信息
	 *
	 * @param userName
	 * @param password
	 * @return {@link Map< String, Object>}
	 * @author Jamin
	 * @date 2021/1/27 9:58
	 */
	public String login(String userName, String password) {
		logger.info("进入方法========{}", userName);
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.equals("admin", userName) && StringUtils.equals("123456", password)) {
			logger.info("true");
			ServiceGroupContext serviceGroupContext = MessageContext.getCurrentMessageContext().getServiceGroupContext();
			serviceGroupContext.setProperty("userName", "admin");
			serviceGroupContext.setProperty("password", 123456);
			result.put("code", "0");
			result.put("msg", "操作完成");
		} else {
			logger.info("false");
			result.put("code", -1);
			result.put("msg", "密码错误");
		}
		logger.info("result=========>{}", result);
		return JSON.toJSONString(result);
	}

	/**
	 * 获取登陆信息
	 *
	 * @param
	 * @return {@link String}
	 * @author Jamin
	 * @date 2021/1/27 9:58
	 */
	public String getUserName() {
		ServiceGroupContext serviceGroupContext = MessageContext.getCurrentMessageContext().getServiceGroupContext();
		Object userName = serviceGroupContext.getProperty("userName");
		if (userName != null) {
			return userName.toString();
		} else {
			return "";
		}
	}

}



