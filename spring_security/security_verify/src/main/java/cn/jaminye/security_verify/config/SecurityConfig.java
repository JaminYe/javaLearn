package cn.jaminye.security_verify.config;

import cn.jaminye.security_verify.filter.MyAuthenticationProvider;
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

import java.io.PrintWriter;
import java.util.Arrays;
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
	/**
	 * 验证码
	 * @return
	 */
	@Bean
	Producer producer() {
		Properties properties = new Properties();
		properties.setProperty("kaptcha.image.width", "150");
		properties.setProperty("kaptcha.image.height", "50");
		properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		Config config = new Config(properties);
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	MyAuthenticationProvider myAuthenticationProvider() {
		MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
		myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		myAuthenticationProvider.setUserDetailsService(userDetailsService());
		return myAuthenticationProvider;
	}


	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		ProviderManager manager = new ProviderManager(Arrays.asList(myAuthenticationProvider()));
		return manager;

	}

	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("123456").roles("admin").build());
		return manager;
	}


	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests()
				.antMatchers("/vc.jpg").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.successHandler((req, resp, auth) -> {
					resp.setContentType("application/json;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.write(new ObjectMapper().writeValueAsString(ResponseEntity.ok(auth.getPrincipal())));
					out.flush();
					out.close();
				})
				.failureHandler((req, resp, e) -> {
					resp.setContentType("application/json;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.write(new ObjectMapper().writeValueAsString(ResponseEntity.badRequest().body(e.getMessage())));
					out.flush();
					out.close();
				})
				.and()
				.userDetailsService(userDetailsService())
				.csrf().disable()
				.build();
	}
}
