package com.expensetracker.expense_tracker.security;

import com.expensetracker.expense_tracker.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
                                "/api/transaction", "/api/category",
                                "api/transaction/**", "/api/category/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/transaction", "/api/category").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/user", "/api/transaction", "/api/category").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,
                                "api/transaction/**").hasRole("USER")
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/category",
                                "/api/category/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/api/transaction", "/api/category").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/transaction", "/api/category").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/category/**", "api/transaction/**").hasRole("ADMIN")
        );
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/api/auth/login").permitAll()
        );
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/api/user/**").permitAll()
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // React app URL
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed methods
        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials like cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
