package cn.jaminye.springcloudzuul.config;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关过滤器
 *
 * @author Jamin
 * @date 2021/3/12 10:25
 */
@Component
public class ZuulFilter extends com.netflix.zuul.ZuulFilter {
	Logger logger = LoggerFactory.getLogger(ZuulFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		logger.info("========>地址{}", request.getRequestURL());
		return null;
	}
}
