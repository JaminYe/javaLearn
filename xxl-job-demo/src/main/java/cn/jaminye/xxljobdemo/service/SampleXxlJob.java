package cn.jaminye.xxljobdemo.service;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/6/23 17:26
 */
@Component
public class SampleXxlJob {

	@XxlJob("demoJobHandler")
	public void demoJobHandler() {
		System.out.println("任务执行成功");
	}

}
