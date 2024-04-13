package com.task.TaskM.Config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.task.TaskM.Service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	@Autowired
	public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		super();
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}


	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request, //extract the request and do things we want
			@NonNull HttpServletResponse response, 
			@NonNull FilterChain filterChain) //contains the list of other filters
			throws ServletException, IOException 
	{
		final String authHeader = request.getHeader("Authorization"); //extract the jwttoken
		final String jwt;
		final String username;
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response); //send request and response to next filter
			return;
		}
		jwt = authHeader.substring(7); //extract jtw token after "Bearer"
		username = jwtService.extractUsername(jwt); //extract the username from JWT token
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
				); //this object is needed to update SecurityHolderContext
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //build the object from our request
				SecurityContextHolder.getContext().setAuthentication(authToken); //update SecurityHolderContext
			}
		}
		filterChain.doFilter(request, response); //send request and response to next filter
	}

}
