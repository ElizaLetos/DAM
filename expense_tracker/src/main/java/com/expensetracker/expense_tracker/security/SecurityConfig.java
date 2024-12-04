package com.expensetracker.expense_tracker.security;

import com.expensetracker.expense_tracker.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/user", "/api/transaction", "/api/category",
                                "/api/user/**", "api/transaction/**", "/api/category/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST,
                                "/api/user", "/api/transaction", "/api/category").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/user", "/api/transaction", "/api/category").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,
                                 "api/transaction/**").hasRole("USER")
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/user", "/api/transaction", "/api/category",
                                "/api/user/**", "api/transaction/**", "/api/category/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/api/user", "/api/transaction", "/api/category").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/user", "/api/transaction", "/api/category").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/user/**", "/api/category/**", "api/transaction/**").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
