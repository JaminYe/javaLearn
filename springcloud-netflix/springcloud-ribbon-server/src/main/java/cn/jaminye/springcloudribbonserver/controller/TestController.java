package cn.jaminye.springcloudribbonserver.controller;

import cn.jaminye.springcloudribbonserver.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jamin
 * @date 2021/3/11 14:39
 */
@RestController
public class TestController {
	@Resource
	TestService testService;

	@GetMapping("/test")
	public String test(String message) {
		return testService.test(message);
	}
}
