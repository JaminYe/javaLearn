package cn.jaminye.security_verify.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 自定义验证提供者
 *
 * @author jaminye
 * @date 2023-02-05 22:36
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
	@Override
	public void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		//if (!((MyWebAuthenticationDetails) authentication.getDetails()).isPassed()) {
		//	throw new AuthenticationServiceException("验证码错误");
		//}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}
}
