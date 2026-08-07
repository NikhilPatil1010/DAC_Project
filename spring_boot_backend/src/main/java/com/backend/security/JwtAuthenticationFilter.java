package com.backend.security;



import java.io.IOException;
import java.net.Authenticator;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.entity.User;
import com.backend.repository.UserRepository;
import com.backend.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil ;
	private final UserRepository userRepository  ;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization") ;
		String token = null ;
	   Long userId = null ;
		if(authHeader != null && authHeader.startsWith("Bearer "))
		{
			token = authHeader.substring(7) ;
			System.out.println("the token  " + token);
			try {
				userId = jwtUtil.getUserIdFromToken( token);
			}
			catch(Exception e)
			{
				log.error("Token is not valid") ;
			}
				
		}
		if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			try {
				if(jwtUtil.validateToken(token) && !jwtUtil.isTokenExpired(token))
				{
					
				User user =	userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user not exist")) ;
				java.util.List<org.springframework.security.core.authority.SimpleGrantedAuthority> authorities = java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user , null , authorities) ;
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
				}
			}
			catch(Exception e)
			{
				log.error("Exception ocuured while validating the request");
			}
			
		}
		
		filterChain.doFilter(request, response);
		

	}

}