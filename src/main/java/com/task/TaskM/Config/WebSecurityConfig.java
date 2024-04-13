 package com.task.TaskM.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	
	@Autowired
	public WebSecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
		super();
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
	}

	// Use this SecurityFilterChain instead of the default one provided by Spring Boot Security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    		.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
    		.authorizeHttpRequests()
    		.requestMatchers("/api/v1/auth/**")
    		.permitAll()
    		.anyRequest()
    		.authenticated()
    		.and()
    		.sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //spring will create a new session for each request
    		.and()
    		.authenticationProvider(authenticationProvider)
    	    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //call jwtAuthenticationFilter before UsernamePasswordAuthenticationFilter
    		//.httpBasic();
    	return http.build();
    }
}