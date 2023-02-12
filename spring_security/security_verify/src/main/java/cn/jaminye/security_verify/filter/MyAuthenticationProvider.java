package cn.jaminye.security_verify.filter;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义验证提供者
 *
 * @author jaminye
 * @date 2023-02-05 22:36
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
	@Override
	protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		final String code = request.getParameter("code");
		final String code1 = (String) request.getSession().getAttribute("code");
		if (code == null || code1 == null || !code.equals(code1)) {
			throw new AuthenticationServiceException("验证码错误");
		}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}
}
