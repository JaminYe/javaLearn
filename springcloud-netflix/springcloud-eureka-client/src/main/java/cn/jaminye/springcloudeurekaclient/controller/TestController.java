package cn.jaminye.springcloudeurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jamin
 * @date 2021/3/11 11:28
 */
@RestController
public class TestController {
	@Value("${server.port}")
	private String port;

	@GetMapping("hi")
	public String hi(String message) {
		return String.format("HI THIS  IS A MESSAGE ======>%s My port is %s", message, port);
	}
}
