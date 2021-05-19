package cn.jaminye.springcloudribbonserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
public class SpringcloudRibbonServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudRibbonServerApplication.class, args);
	}

}
