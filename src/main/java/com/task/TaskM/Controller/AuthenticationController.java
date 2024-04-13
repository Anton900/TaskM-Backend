package com.task.TaskM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.TaskM.Model.AuthenticationResponse;
import com.task.TaskM.Model.AuthenticationRequest;
import com.task.TaskM.Model.RegisterRequest;
import com.task.TaskM.Service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@Autowired
	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authenticationService.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
	
}
