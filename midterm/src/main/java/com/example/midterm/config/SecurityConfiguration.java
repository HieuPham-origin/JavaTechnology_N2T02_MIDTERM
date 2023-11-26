package com.example.midterm.config;

import com.example.midterm.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailService userDetailService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeHttpRequests(
                authorized -> authorized.requestMatchers(
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/api/products/**",
                        "/register/**"
                ).permitAll().requestMatchers("/index","/").permitAll()
                        .requestMatchers("register","/register","/register/**")
                        .permitAll()
        );
        return http.build();
    }
}
