package cn.jaminye.spring_security_demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jaminye
 * @date 2021/11/7 上午9:50
 */
@RestController
public class TestController {
	@GetMapping("hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping("/index")
	public String index() {
		return "这是首页";
	}

	@GetMapping("/admin/hello")
	public String admin() {
		return "admin";
	}

	@GetMapping("/user/hello")
	public String user() {
		return "user";
	}
}
