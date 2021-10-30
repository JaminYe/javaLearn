package cn.jaminye.netty_socketio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jaminye
 * @date 2021/10/30 下午4:13
 */
@Controller
public class MainController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

}
