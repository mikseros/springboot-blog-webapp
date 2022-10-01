package com.mikseros.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSpringSecurity {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().disable()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/admin/posts")
						.loginProcessingUrl("/login")
						.permitAll()
				);
		return http.build();
							
	}
}
