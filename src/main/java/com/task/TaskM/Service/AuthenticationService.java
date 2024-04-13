package com.task.TaskM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.TaskM.Entity.Role;
import com.task.TaskM.Entity.User;
import com.task.TaskM.Model.AuthenticationRequest;
import com.task.TaskM.Model.AuthenticationResponse;
import com.task.TaskM.Model.RegisterRequest;
import com.task.TaskM.Repository.UserRepository;

@Service
public class AuthenticationService {
	
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	@Autowired
	public AuthenticationService(UserRepository userRepository, JwtService jwtService, 
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationResponse register(RegisterRequest request) {
		var user = new User(request.getFirstname(), request.getLastname(), 
				request.getEmail(), passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.USER);
		userRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getEmail(), request.getPassword())
		);
		//user is authenticated if here
		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}

}
