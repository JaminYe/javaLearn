package cn.jaminye.springcloudribbonserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Jamin
 * @date 2021/3/11 14:33
 */
@Service
public class TestService {
	@Resource
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "hiError")
	public String test(String message) {
		return restTemplate.getForObject("http://springcloud-eureka/hi?message=" + message, String.class);
	}

	public String hiError(String message) {
		return "==========>error";
	}
}
