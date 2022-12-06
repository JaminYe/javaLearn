package cn.jaminye.rememberme_simple_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	@GetMapping("/rememberme")
	public String rememberme() {
		return "rememberme";
	}
}