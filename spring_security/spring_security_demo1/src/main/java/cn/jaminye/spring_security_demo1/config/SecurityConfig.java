package cn.jaminye.spring_security_demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jaminye
 * @date 2021/11/7 上午10:34
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	PasswordEncoder passwordEncoder() {
		//不加密
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//两个用户,中间使用and相连
		auth.inMemoryAuthentication().withUser("jaminye").password("123456").roles("admin")
				.and().withUser("user").password("12354").roles("user");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//不拦截静态资源
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//指定登陆页面
		http.authorizeRequests().anyRequest().authenticated()
				.and()
				.formLogin()
				//登陆页面配置
				.loginPage("/login.html")
				//默认与loginPage相同 详情见源码FormLoginConfigurer.init super.init updateAuthenticationDefaults
				.loginProcessingUrl("/login.do")
				//默认username,password, 详情见源码FormLoginConfigurer的构造函数
				.usernameParameter("name")
				.passwordParameter("passwd")
				//第二个参数不设置默认false,登陆后返回来源页面,第二个参数设置为true同successForwardUrl始终返回设置的页面
				.defaultSuccessUrl("/index")
				// .successForwardUrl("/index")
				.permitAll()
				.and()
				.csrf().disable();
	}
}
