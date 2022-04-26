package cn.jaminye.aspectlog.controller;

import cn.jaminye.aspectlog.annotation.Log;
import cn.jaminye.aspectlog.service.TestService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Jamin
 * @date 2022/4/22 16:00
 */
@RestController
public class TestController {
	@Resource
	TestService testService;

	@Log(servName = "")
	@RequestMapping("test")
	public List<Map<String, Object>> test(@RequestBody Map<String, Object> map) {
		return testService.test();
	}


	@Log(servName = "")
	@RequestMapping("test1")
	public List<Map<String, Object>> test1(String id, String name) {
		return testService.test();
	}

	@Log(servName = "")
	@RequestMapping("test2")
	public List<Map<String, Object>> test(MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		return testService.test();
	}
}
