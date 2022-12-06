package cn.jaminye.rememberme_simple_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Jamin
 * @date 2022/3/20 11:37
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	DataSource dataSource;

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	JdbcTokenRepositoryImpl jdbcTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("test").password("123").roles("admin");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//必须是自动登陆才能访问
				.antMatchers("/rememberme").rememberMe()
				//必须是用户名密码才能访问
				.antMatchers("/admin").fullyAuthenticated()
				.anyRequest().authenticated()
				.and().formLogin()
				.and().rememberMe().key("test").tokenRepository(jdbcTokenRepository()).and().csrf().disable();
	}
}
