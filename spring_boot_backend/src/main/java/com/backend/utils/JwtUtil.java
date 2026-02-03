package com.backend.utils;


import java.awt.datatransfer.StringSelection;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	   @Value("${jwt.secret}")
	    private String jwtSecretKey;

	    @Value("${jwt.expiration}")
	    private long jwtExpiration;
	    
	    public String genrateToken(Long userId)
	    {
	    	Date now = new Date() ;
	    	Date expireyDate = new Date(now.getTime() + jwtExpiration) ;
	    	String id = String.valueOf(userId);
	    	return Jwts.builder()
	    			.setSubject(id)
	    			.setIssuedAt(now)
	    			.setExpiration(expireyDate)
	    			.signWith(getSigninKey())
	    			.compact();
	    	
	    	
	    }

		private Key getSigninKey() {
			// TODO Auto-generated method stub
			return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
		}

		
		
		public Long getUserIdFromToken(String token) {
			// TODO Auto-generated method stub
			@SuppressWarnings("deprecation")
			Claims claims = Jwts.parser()
					.setSigningKey(getSigninKey())
					.parseClaimsJws(token)
					.getBody() ;
			return Long.parseLong(claims.getSubject() );
		}
		
		public boolean validateToken(String token)
		{
			try {
				Jwts.parser()
				.setSigningKey(getSigninKey()) 
				.parseClaimsJws(token) ;
				return true ;
			}
			catch(JwtException | IllegalArgumentException e)
			{
				return false ;
			}
		}
		
		public boolean isTokenExpired(String token)
		{
			try {
				Claims claims  = Jwts.parser()
				.setSigningKey(getSigninKey()) 
				.parseClaimsJws(token)
				.getBody();
				return claims.getExpiration().before(new Date()) ;
			}
			catch(JwtException | IllegalArgumentException e)
			{
				return true ;
			}
		}
	    
}
