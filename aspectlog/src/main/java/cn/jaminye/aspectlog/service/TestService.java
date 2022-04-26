package cn.jaminye.aspectlog.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jamin
 * @date 2022/4/22 15:54
 */
@Service
public class TestService {


	public List<Map<String, Object>> test() {
		Map<String, Object> map = new HashMap<>();
		map.put("1", "2");
		map.put("2", "3");
		return Collections.singletonList(map);
	}

}
