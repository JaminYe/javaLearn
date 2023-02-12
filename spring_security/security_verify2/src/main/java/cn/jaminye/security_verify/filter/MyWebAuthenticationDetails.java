package cn.jaminye.security_verify.filter;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jaminye
 * @date 2023-02-12 20:51
 */
public class MyWebAuthenticationDetails extends WebAuthenticationDetails {
	private static final long serialVersionUID = 1292293156703216134L;
	/**
	 * 是否通过
	 */
	private boolean isPassed;

	public MyWebAuthenticationDetails(final HttpServletRequest request) {
		super(request);
		final String code = request.getParameter("code");
		final String code1 = (String) request.getSession().getAttribute("code");
		if (code != null && code.equals(code1)) {
			this.isPassed = true;
		}
	}

	public boolean isPassed() {
		return this.isPassed;
	}
}
