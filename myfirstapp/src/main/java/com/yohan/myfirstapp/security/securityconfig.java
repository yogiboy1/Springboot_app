package com.yohan.myfirstapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityconfig {

	@Bean
	public SecurityFilterChain mySecurityFilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeHttpRequests(auth->auth.requestMatchers("/h2-console/**","/css/**","/js/**").permitAll().requestMatchers("/swagger-ui/**","/select/sender").permitAll().requestMatchers("/select/login").permitAll())
		.formLogin().loginPage("/select/login").loginProcessingUrl("/select/login").defaultSuccessUrl("/home").permitAll();
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

