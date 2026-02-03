package com.backend.config;



import java.util.Arrays;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.backend.security.JwtAuthenticationEntryPoint;
import com.backend.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter ;
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder() ;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.cors(cors -> cors.configurationSource(corsConfigrationSource()))
		.csrf(csrf ->csrf.disable())
		  .authorizeHttpRequests(auth -> auth
				     .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                .requestMatchers("/api/auth/login" , "/api/auth/register" , "/swagger-ui/**",
	                		"/api/**",
	                		  "/v3/api-docs/**",
	                          "/swagger-resources/**",
	                          "/webjars/**" 
	                          ).permitAll()
	                .anyRequest().authenticated())
		  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		  .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		  .exceptionHandling(ex -> ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
		  ;
		return http.build() ;
	}
	@Bean 
	public CorsConfigurationSource corsConfigrationSource()
	{
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173" ,   "http://localhost:8080" ));
		configuration.setAllowedMethods(Arrays.asList("GET" , "POST", "PUT","PATCH" ,"OPTIONS" , "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source  = new UrlBasedCorsConfigurationSource() ;
		source.registerCorsConfiguration("/**", configuration) ;
		return source ;
		
	}

}