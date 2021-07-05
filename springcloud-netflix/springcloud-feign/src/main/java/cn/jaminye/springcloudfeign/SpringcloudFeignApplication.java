package cn.jaminye.springcloudfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
// @EnableHystrixDashboard
// @EnableCircuitBreaker
public class SpringcloudFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudFeignApplication.class, args);
	}

}
