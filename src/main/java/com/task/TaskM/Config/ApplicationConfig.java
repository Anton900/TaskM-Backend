package com.task.TaskM.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.task.TaskM.Repository.UserRepository;

@Configuration
public class ApplicationConfig {

	private final UserRepository userResposistory;
	
	@Autowired
	public ApplicationConfig(UserRepository userResposistory) {
		super();
		this.userResposistory = userResposistory;
	}
 
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userResposistory.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
	
	
	//data access object that is responsible for fetching userdetails and also encode password and so forth
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService()); //need to specify which userdetailsserivce to use so it will fetch our users
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
