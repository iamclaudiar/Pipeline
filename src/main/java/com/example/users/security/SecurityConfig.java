package com.example.users.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/users/**").permitAll()
//                        .requestMatchers("/api/users/**").authenticated() // Require authentication for specific endpoints
                        .anyRequest().permitAll() // Allow all other requests
                )
                .httpBasic(httpBasic -> {}); // Enables HTTP Basic Authentication

        return http.build();
    }
}
