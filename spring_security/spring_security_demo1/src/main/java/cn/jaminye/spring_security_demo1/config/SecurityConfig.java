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
				.loginProcessingUrl("/doLogin")
				//默认username,password, 详情见源码FormLoginConfigurer的构造函数
				.usernameParameter("name")
				.passwordParameter("passwd")
				//上方接口不需要登陆验证
				//第二个参数不设置默认false,登陆后返回来源页面,第二个参数设置为true同successForwardUrl始终返回设置的页面,
				// 但是successForwardUrl 是转发,defaultSuccessUrl重定向
				// .defaultSuccessUrl("/index",true)
				.successForwardUrl("/index")
				.permitAll()
				.and()
				.logout()
				//注销登陆的url,默认logout
				.logoutUrl("/logoutUrl")
				//与logout类似,可指定请求方式
				// .logoutRequestMatcher(new AntPathRequestMatcher("/logoutUrl", "POST"))
				// 注销成功后跳转地址
				.logoutSuccessUrl("/index")
				//清楚cookie
				.deleteCookies()
				//清除cookie和使session失效,默认就会执行
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.permitAll()
				.and()
				.csrf().disable();
	}
}
