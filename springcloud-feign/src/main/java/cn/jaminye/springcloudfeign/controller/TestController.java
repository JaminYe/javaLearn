package cn.jaminye.springcloudfeign.controller;

import cn.jaminye.springcloudfeign.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jamin
 * @date 2021/3/11 15:31
 */
@RestController
public class TestController {
	@Autowired
	TestService testService;


	@RequestMapping("hi")
	public String test(String message) {
		return testService.hiMessage(message);
	}


}
