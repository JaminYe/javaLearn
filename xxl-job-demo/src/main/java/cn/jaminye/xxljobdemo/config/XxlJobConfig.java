package cn.jaminye.xxljobdemo.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jamin
 * @date 2021/6/23 17:22
 */
@Configuration
public class XxlJobConfig {

	@Value("${xxl.job.admin.addresses}")
	private String adminAdresses;

	@Value("${xxl.job.executor.appname}")
	private String appname;

	@Bean
	public XxlJobSpringExecutor xxlJobSpringExecutor() {
		XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
		xxlJobSpringExecutor.setAdminAddresses(adminAdresses);
		xxlJobSpringExecutor.setAppname(appname);
		return xxlJobSpringExecutor;
	}

}
