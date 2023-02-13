package cn.jaminye.security_session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.annotation.Resource;

/**
 * @author jaminye
 * @date 2023-02-13 22:16
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Resource
	FindByIndexNameSessionRepository sessionRepository;

	@Bean
	SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {

		return http.authorizeRequests().anyRequest().authenticated().and().formLogin()
				.and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true)
				.sessionRegistry(this.sessionRegistry()).and().and().csrf().disable().build();
	}

	@Bean
	SpringSessionBackedSessionRegistry sessionRegistry() {
		return new SpringSessionBackedSessionRegistry(this.sessionRepository);
	}

}
