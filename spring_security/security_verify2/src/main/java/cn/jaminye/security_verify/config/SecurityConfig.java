package cn.jaminye.security_verify.config;

import cn.jaminye.security_verify.filter.MyAuthenticationProvider;
import cn.jaminye.security_verify.filter.MyWebAuthenticationDetailsSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Properties;

/**
 * spring security 5.7+
 *
 * @author jaminye
 * @date 2022-12-06 15:51
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Resource
	MyWebAuthenticationDetailsSource myWebAuthenticationDetailsSource;

	/**
	 * 验证码
	 *
	 * @return
	 */
	@Bean
	Producer producer() {
		final Properties properties = new Properties();
		properties.setProperty("kaptcha.image.width", "150");
		properties.setProperty("kaptcha.image.height", "50");
		properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		final Config config = new Config(properties);
		final DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	MyAuthenticationProvider myAuthenticationProvider() {
		final MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
		myAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		myAuthenticationProvider.setUserDetailsService(this.userDetailsService());
		return myAuthenticationProvider;
	}


	@Bean
	AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(this.myAuthenticationProvider()));
	}

	@Bean
	UserDetailsService userDetailsService() {
		final InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("123456").roles("admin").build());
		return manager;
	}

	/**
	 * 默认的失效是通过调用 StandardSession#invalidate 导致 Spring Security无法及时清理会话信息表 以为用户还在线
	 * bean的作用为将session创建和销毁事件发布出去
	 */
	@Bean
	HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	HttpFirewall httpFirewall() {
		final StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowSemicolon(true);
		return firewall;
	}

	@Bean
	SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests()
				.antMatchers("/vc.jpg").permitAll()
				//有先后顺序
				.antMatchers("/admin/**").hasRole("admin")
				.antMatchers("/user/**").hasRole("user")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.authenticationDetailsSource(this.myWebAuthenticationDetailsSource)
				.successHandler((req, resp, auth) -> {
					resp.setContentType("application/json;charset=utf-8");
					final PrintWriter out = resp.getWriter();
					out.write(new ObjectMapper().writeValueAsString(ResponseEntity.ok(auth.getPrincipal())));
					out.flush();
					out.close();
				})
				.failureHandler((req, resp, e) -> {
					resp.setContentType("application/json;charset=utf-8");
					final PrintWriter out = resp.getWriter();
					out.write(new ObjectMapper().writeValueAsString(ResponseEntity.badRequest().body(e.getMessage())));
					out.flush();
					out.close();
				})
				.and()
				.csrf().disable()
				.sessionManagement()
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true)
				.and().and().build();
	}

}