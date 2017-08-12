package com.exquizme;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@EnableOAuth2Sso
@SpringBootApplication
public class ExquizmeApplication extends WebSecurityConfigurerAdapter {
	public static void main(String[] args) {
		String property = System.getProperty("spring.profiles.active");
		log.debug("spring.profiles.active: {}", property);

		SpringApplication.run(ExquizmeApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.antMatcher("/**") // TODO: 로그인 필요한 요청들만 여기에 패턴으로 지정
				.authorizeRequests()
				.antMatchers("/**")
				.permitAll()
				.anyRequest()
				.authenticated();
	}
}
