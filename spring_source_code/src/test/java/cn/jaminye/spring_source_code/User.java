package cn.jaminye.spring_source_code;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
public class User implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean {
	private String name;

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("setBeanClassLoader");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("setBeanName");
	}


	@PostConstruct
	public void postConstruct(){
        System.out.println("postConstruct");
    }


	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
	}
}
