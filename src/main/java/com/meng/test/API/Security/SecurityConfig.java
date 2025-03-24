package com.meng.test.API.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.meng.test.API.Jwt.FilterChainExceptionHandler;
import com.meng.test.API.Jwt.JwtLoginFilter;
import com.meng.test.API.Jwt.TokenVerifyFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserService userService;
	private final FilterChainExceptionHandler filterChainExceptionHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll()
//                .requestMatchers(HttpMethod.PUT, "/api/permission/**").hasAuthority(PermissionEnum.PERMISSION_WRITE.getDescription())
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            )
            .addFilterBefore(filterChainExceptionHandler, JwtLoginFilter.class)
            .addFilter(new JwtLoginFilter(authenticationManager))
            .addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userService.getByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }

//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("Meng")
//                .password(passwordEncoder().encode("112233"))
//                .authorities(RoleEnum.ADMIN.grantedAuthority())
//                .build();
//
//        UserDetails user2 = User.withUsername("Sok")
//                .password(passwordEncoder().encode("11223344"))
//                .authorities(RoleEnum.SALE.grantedAuthority())
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
