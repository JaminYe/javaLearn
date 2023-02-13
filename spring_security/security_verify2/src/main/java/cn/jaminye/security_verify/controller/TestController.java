package cn.jaminye.security_verify.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
	public void vc(final HttpServletResponse response, final HttpSession session) throws IOException {
		final String text = this.defaultKaptcha.createText();
		final BufferedImage image = this.defaultKaptcha.createImage(text);
		session.setAttribute("code", text);
		response.setContentType("image/jpeg");
		try (final ServletOutputStream out = response.getOutputStream()) {
			ImageIO.write(image, "jpg", out);
		}
	}

	@GetMapping("/getInfo")
	public String getInfo() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getDetails().toString();

	}


	@RequestMapping(value = "/hello/{id}")
	public void hello(@PathVariable final Integer id, @MatrixVariable final String name) {
		System.out.println("id = " + id);
		System.out.println("name = " + name);
	}
}