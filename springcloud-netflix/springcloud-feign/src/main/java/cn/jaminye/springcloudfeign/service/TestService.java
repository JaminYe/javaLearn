package cn.jaminye.springcloudfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jamin
 * @date 2021/3/11 15:24
 */
@FeignClient(value = "springcloud-eureka")
@Service
public interface TestService {

	@GetMapping("hi")
	public String hiMessage(@RequestParam("message") String message);

}
