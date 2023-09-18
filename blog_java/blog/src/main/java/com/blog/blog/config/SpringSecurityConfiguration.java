package com.blog.blog.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.blog.blog.exception.JWTAuthenticationEntryPoint;
import com.blog.blog.jwt.JwtFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration // annotation này để spring biết đây là file cấu hình
// @EnableWebSecurity //annotation này cấu hình phân quyền với url
@EnableMethodSecurity // annotation này cấu hình phân quyền trên method với hasRole, hasAuthority
// http://localhost:8080/swagger-ui/index.html
// start cấu hình openapi
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SpringSecurityConfiguration {

    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtFilter jwtFilter;

    public SpringSecurityConfiguration(JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtFilter jwtFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtFilter = jwtFilter;
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

        http
                // .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable()) // disable csrf b?o v?
                .cors(Customizer.withDefaults())
                // lu?ng auth cho truy c?p mà không c?n b?o v?
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        // .requestMatchers("/api/v1/posts/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll() // cấu hình swagger public
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                // b?t exception ? ?ây -- các excetion không có quy?n truy c?p tài nguyên
                .exceptionHandling((ex) -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                // disable session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // thêm bộ lọc jwtFilter vào chuỗi bộ lọc trước bộ lọc
        // UsernamePasswordAuthenticationFilter
        // nếu có jwt thì set kiểm tra jwtFilter thỏa mãn, người dùng đăng nhập thành
        // công
        // nếu không có jwt thì kiểm tra UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
