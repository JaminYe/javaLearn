package cn.jaminye.rememberme_simple_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jamin
 * @date 2022/3/20 11:40
 */
@RestController
public class TestController {

	@RequestMapping("hello")
	public String hello() {
		return "123";
	}
}
