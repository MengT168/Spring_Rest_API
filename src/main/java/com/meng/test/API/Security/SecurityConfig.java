package com.meng.test.API.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll().anyRequest().authenticated())
				.httpBasic(withDefaults -> {
				});
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {

		UserDetails user1 = User.withUsername("Meng").password(passwordEncoder().encode("112233")).build();

		UserDetails user2 = User.withUsername("Sok").password(passwordEncoder().encode("11223344")).build();

		return new InMemoryUserDetailsManager(user1, user2);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
