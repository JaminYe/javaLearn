package cn.jaminye.spring_security_demo1.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jamin
 * @date 2022/1/23 11:24
 * 自定义验证器
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

	public LoginFilter() {
		this.setUsernameParameter("name");
		this.setPasswordParameter("passwd");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		// 校验请求方式
		if (!"POST".equals(request.getMethod())) {
			throw new AuthenticationServiceException("method is not supported");
		}
		//json格式
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			//存储登陆信息
			Map<String, String> loginData = new HashMap<>(8);
			try {
				//	获取登陆信息
				loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			} catch (IOException e) {
				throw new AuthenticationServiceException("get loginData not failed");
			}

			String userName = loginData.get(getUsernameParameter());
			String password = loginData.get(getPasswordParameter());
			if (Objects.isNull(userName)) {
				userName = "";
			}
			if (Objects.isNull(password)) {
				password = "";
			}
			userName = userName.trim();
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);
			setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		} else {
			return super.attemptAuthentication(request, response);
		}
	}


	@Override
	public RememberMeServices getRememberMeServices() {
		return super.getRememberMeServices();
	}

	@Override
	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		super.setRememberMeServices(rememberMeServices);
	}
}
