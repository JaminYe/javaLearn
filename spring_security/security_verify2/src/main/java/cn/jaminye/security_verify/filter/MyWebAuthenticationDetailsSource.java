package cn.jaminye.security_verify.filter;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jaminye
 * @date 2023-02-12 21:05
 */
@Component
public class MyWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, MyWebAuthenticationDetails> {
	@Override
	public MyWebAuthenticationDetails buildDetails(final HttpServletRequest context) {
		return new MyWebAuthenticationDetails(context);
	}
}
