package com.meng.test.API.Jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenVerifyFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		 if (header == null || !header.startsWith("Bearer ")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
		
		String token = header.replace("Bearer ", "");
		String secretKey = "a-string-secret-at-least-256-bits-long";
		
		Jws<Claims> claimsJws = Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
			.build()
            .parseClaimsJws(token);
            
		Claims body = claimsJws.getBody();
		
		String username = body.getSubject();
		List<Map<String,String>> authorities = (List<Map<String, String>>) body.get("authorities");
		
		Set<SimpleGrantedAuthority> grantedAuthority = authorities.stream().map(x-> new SimpleGrantedAuthority(x.get("authority"))).collect(Collectors.toSet());
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthority);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

}
