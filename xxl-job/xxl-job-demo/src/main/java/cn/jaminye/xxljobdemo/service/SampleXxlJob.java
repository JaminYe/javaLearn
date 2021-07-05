package cn.jaminye.xxljobdemo.service;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/6/23 17:26
 */
@Component
public class SampleXxlJob {
	static Logger logger = LoggerFactory.getLogger(SampleXxlJob.class);

	@XxlJob("demoJobHandler")
	public void demoJobHandler() throws InterruptedException {
		//控制台日志
		logger.info("123控制台打印");
		// 执行日志
		XxlJobHelper.log("123执行日志打印");
		Thread.sleep(2000);
		XxlJobHelper.handleFail("123");
	}
}
