package cn.jaminye.security_session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {

	@Value("${server.port}")
	Integer port;

	@GetMapping("/set")
	public String set(final HttpSession session) {
		session.setAttribute("user", "jaminye");
		return String.valueOf(this.port);
	}

	@GetMapping("/get")
	public String get(final HttpSession session) {
		return session.getAttribute("user") + ":" + this.port;
	}
}
