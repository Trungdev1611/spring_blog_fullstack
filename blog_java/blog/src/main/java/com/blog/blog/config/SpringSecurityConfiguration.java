package com.blog.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blog.blog.exception.JWTAuthenticationEntryPoint;

@Configuration // annotation này để spring biết đây là file cấu hình
@EnableWebSecurity
public class SpringSecurityConfiguration {

    JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SpringSecurityConfiguration(JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // disable csrf bảo vệ

                // luồng auth cho truy cập mà không cần bảo vệ
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, "/").permitAll()

                        .requestMatchers("/api/auth/**").permitAll()

                        .anyRequest().authenticated())
                // bắt exception ở đây -- các excetion không có quyền truy cập tài nguyên
                .exceptionHandling((ex) -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                // disable session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // http.addFilterBefore(jwtAuthenticationFilter,
        // UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
