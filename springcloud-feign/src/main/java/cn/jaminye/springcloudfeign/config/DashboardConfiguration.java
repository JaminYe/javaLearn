package cn.jaminye.springcloudfeign.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jamin
 * @date 2021/3/11 17:39
 */
@Configuration
public class DashboardConfiguration {
	@Bean
	public ServletRegistrationBean getServlet() {

		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("hystrixMetricsStreamServlet");
		return registrationBean;

	}
}
