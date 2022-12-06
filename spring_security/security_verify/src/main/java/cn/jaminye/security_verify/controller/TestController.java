package cn.jaminye.security_verify.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class TestController {

	@Resource
	Producer defaultKaptcha;

	@GetMapping("/vc.jpg")
	public void vc(HttpServletResponse response, HttpSession session) throws IOException {
		String text = defaultKaptcha.createText();
		BufferedImage image = defaultKaptcha.createImage(text);
		session.setAttribute("code", text);
		response.setContentType("image/jpeg");
		try (ServletOutputStream out = response.getOutputStream()) {
			ImageIO.write(image, "jpg", out);
		}
	}

	@GetMapping("/getInfo")
	public String getInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getDetails().toString();

	}
}