package cn.jamin.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jamin
 * @date 2020/12/29 21:05
 */
@Controller
public class ChatController {
	@RequestMapping("chat")
	public String chatIndex(ModelAndView modelAndView) {
		return "index";
	}
}
