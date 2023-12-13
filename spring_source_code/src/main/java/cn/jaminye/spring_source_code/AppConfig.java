package cn.jaminye.spring_source_code;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


	@Bean(initMethod = "postConstruct", destroyMethod = "destroy")
	public User user() {
		return new User();
	}






}

