package com.task.TaskM.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	// Use this SecurityFilterChain instead of the default one provided by Spring Boot Security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    		.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
    		.authorizeRequests()
    		.anyRequest()
    		.authenticated()
    		.and()
    		.httpBasic();
    	return http.build();
    }
}