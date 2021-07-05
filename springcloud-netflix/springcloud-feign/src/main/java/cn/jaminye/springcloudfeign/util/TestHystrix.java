package cn.jaminye.springcloudfeign.util;

import cn.jaminye.springcloudfeign.service.TestService;
import org.springframework.stereotype.Component;

/**
 * @author Jamin
 * @date 2021/3/11 17:29
 */
@Component
public class TestHystrix implements TestService {
	@Override
	public String hiMessage(String message) {
		return "error=============>" + message;
	}
}
