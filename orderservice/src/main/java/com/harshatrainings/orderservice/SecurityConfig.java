package com.harshatrainings.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Autowired
	JwtAuthConverter jwtAuthCOnvertor;
	
	@Bean
	SecurityFilterChain initSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize->authorize
				.requestMatchers("/prodcts/**").hasAnyRole("ADMIN")
				.requestMatchers("/hellouser/**").hasAnyRole("USER")
				.anyRequest().authenticated())
		.oauth2ResourceServer(oAuth-> oAuth.jwt(jwt-> jwt.jwtAuthenticationConverter(jwtAuthCOnvertor)));
		return http.build();
	}
	
}
