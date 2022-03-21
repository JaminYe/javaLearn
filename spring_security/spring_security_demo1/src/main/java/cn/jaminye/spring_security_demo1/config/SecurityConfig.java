package cn.jaminye.spring_security_demo1.config;

import cn.jaminye.spring_security_demo1.entity.User;
import cn.jaminye.spring_security_demo1.filter.LoginFilter;
import cn.jaminye.spring_security_demo1.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jaminye
 * @date 2021/11/7 上午10:34
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	DataSource dataSource;
	@Resource
	UserService userService;

	@Bean
	PasswordEncoder passwordEncoder() {
		//不加密
		return NoOpPasswordEncoder.getInstance();
	}


	//配置用户密码角色 两种方法

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//两个用户,中间使用and相连
		// auth.inMemoryAuthentication().withUser("jaminye").password("123456").roles("admin")
		// 		.and().withUser("user").password("12354").roles("user");
		auth.userDetailsService(userService);
	}

	/*@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		// 内存
		*//*InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("jaminye").password("123456").roles("admin").build());
		manager.createUser(User.withUsername("user").password("12354").roles("user").build());
		return manager;*//*
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		//初始化数据
		if (!manager.userExists("jaminye")) {
			manager.createUser(User.withUsername("jaminye").password("123456").roles("admin").build());
		}
		if (!manager.userExists("user")) {
			manager.createUser(User.withUsername("user").password("12354").roles("user").build());
		}
		return manager;
	}*/

	@Override
	public void configure(WebSecurity web) throws Exception {
		//不拦截静态资源
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//指定登陆页面
		http.authorizeRequests()
				//有先后顺序
				.antMatchers("/admin/**").hasRole("admin")
				.antMatchers("/user/**").hasRole("user")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				//登陆页面配置
				.loginPage("/login.html")
				//默认与loginPage相同 详情见源码FormLoginConfigurer.init super.init updateAuthenticationDefaults
				.loginProcessingUrl("/doLogin")
				//默认username,password, 详情见源码FormLoginConfigurer的构造函数
				// 自定义这里没用了
				.usernameParameter("name")
				.passwordParameter("passwd")
				//上方接口不需要登陆验证
				//第二个参数不设置默认false,登陆后返回来源页面,第二个参数设置为true同successForwardUrl始终返回设置的页面,
				// 但是successForwardUrl 是转发,defaultSuccessUrl重定向
				// .defaultSuccessUrl("/index",true)
				.successForwardUrl("/index")
				.permitAll()
				.and()
				// .rememberMe()
				// .rememberMeParameter("remember").key("test")
				// .and()
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
		//添加过滤器
		http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
	}


	@Bean
	LoginFilter loginFilter() throws Exception {
		LoginFilter loginFilter = new LoginFilter();
		//成功
		loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			// User user = (User) authentication.getPrincipal();
			User user = (User) authentication.getPrincipal();
			Map<String, Object> result = new HashMap<>(8);
			result.put("code", 0);
			result.put("msg", user.getUsername() + "登陆成功");
			result.put("data", user.getAuthorities());
			out.write(new ObjectMapper().writeValueAsString(result));
			out.flush();
			out.close();
		});
		//失败根据异常返回
		loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			Map<String, Object> result = new HashMap<>(8);
			if (exception instanceof LockedException) {
				result.put("code", 100);
				result.put("msg", "账户被锁");
			} else if (exception instanceof CredentialsExpiredException) {
				result.put("code", 200);
				result.put("msg", "密码过期");
			} else if (exception instanceof AccountExpiredException) {
				result.put("code", 300);
				result.put("msg", "账户过期");
			} else if (exception instanceof DisabledException) {
				result.put("code", 400);
				result.put("msg", "账户禁用");
			} else if (exception instanceof BadCredentialsException) {
				result.put("code", 500);
				result.put("msg", "用户密码错误");
			} else {
				result.put("code", 600);
				result.put("msg", "登陆失败");
			}
			out.write(new ObjectMapper().writeValueAsString(result));
			out.flush();
			out.close();
		});
		loginFilter.setAuthenticationManager(authenticationManagerBean());
		loginFilter.setFilterProcessesUrl("/doLogin");
		return loginFilter;
	}

	@Bean
	RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		//admin继承user的权限
		roleHierarchy.setHierarchy("ROLE_admin > ROLE_user");
		return roleHierarchy;
	}

}
