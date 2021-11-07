package cn.jaminye.spring_security_demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/index")
	public String index(){
		return "这是首页";
	}
}
