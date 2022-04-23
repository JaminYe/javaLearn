package cn.jaminye.aspectlog.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Jamin
 * @date 2022/4/22 15:42
 */
@Component
@Aspect
public class LogAspect {
	@Pointcut("@annotation(cn.jaminye.aspectlog.annotation.Log)")
	public void pointCut() {

	}


	@AfterReturning(value = "pointCut()", returning = "jsonResult")
	public void afterReturning(JoinPoint joinPoint, Object jsonResult) {
		handleLog(joinPoint, null, jsonResult);
	}

	private void handleLog(JoinPoint joinPoint, Exception ex, Object jsonResult) {
		System.out.println(joinPoint.getSignature().getName());
		System.out.println(joinPoint.getSignature().getDeclaringTypeName());
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		String method = request.getMethod();
		if (method.equalsIgnoreCase("put") || method.equalsIgnoreCase("post")) {
			String params = argsArrayToString(joinPoint.getArgs());
			System.out.println("jiexi=====>" + params);
		} else {
			System.out.println(JSON.toJSONString(request.getParameterMap()));
		}
		System.out.println(jsonResult);
	}

	private String argsArrayToString(Object[] args) {
		StringBuilder params = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			Object arg = args[i];
			if (arg != null && !isFilterObject(arg)) {
				params.append(JSON.toJSONString(args[i]));
			}
		}
		return params.toString();

	}

	private boolean isFilterObject(final Object arg) {
		Class<?> clazz = arg.getClass();
		if (clazz.isArray()) {
			//此类的父类或者接口是不是文件类
			return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
		} else if (Collection.class.isAssignableFrom(clazz)) {
			Collection collection = (Collection) arg;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); ) {
				return iterator.next() instanceof MultipartFile;
			}
		} else if (Map.class.isAssignableFrom(clazz)) {
			Map map = (Map) arg;
			for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
				Map.Entry entry = (Map.Entry) iterator.next();
				return entry.getValue() instanceof MultipartFile;
			}
		}
		return arg instanceof MultipartFile || arg instanceof HttpServletRequest || arg instanceof HttpServletResponse || arg instanceof BindingResult;
	}
}
