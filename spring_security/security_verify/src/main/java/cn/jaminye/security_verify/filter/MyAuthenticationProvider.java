package cn.jaminye.security_verify.filter;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class MyAuthenticationProvider extends DaoAuthenticationProvider {
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String code = request.getParameter("code");
		String code1 = (String) request.getSession().getAttribute("code");
		if (code == null || code1 == null || !code.equals(code1)) {
			throw new AuthenticationServiceException("验证码错误");
		}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}
}
