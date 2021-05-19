package cn.jaminye.springcloudfeign.controller;

import cn.jaminye.springcloudfeign.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jamin
 * @date 2021/3/11 15:31
 */
@RestController
public class TestController {
	@Resource
	TestService testService;

	@RequestMapping("hi")
	public String test(String message) {
		return testService.hiMessage(message);
	}


}
