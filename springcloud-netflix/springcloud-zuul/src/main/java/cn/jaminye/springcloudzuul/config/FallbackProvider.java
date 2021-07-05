package cn.jaminye.springcloudzuul.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 未实现
 *
 * @author Jamin
 * @date 2021/3/12 10:09
 */
@Component
public class FallbackProvider implements org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider {
	@Override
	public String getRoute() {
		return null;
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> map = new HashMap<>();
				map.put("status", 200);
				map.put("message", "无法连接,请检查网络");
				return new ByteArrayInputStream(objectMapper.writeValueAsString(map).getBytes("utf-8"));
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				return httpHeaders;
			}
		};
	}
}
