package com.blog.blog.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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

import com.blog.blog.exception.JWTAuthenticationEntryPoint;
import com.blog.blog.jwt.JwtFilter;

@Configuration // annotation này để spring biết đây là file cấu hình
// @EnableWebSecurity //annotation này cấu hình phân quyền với url
@EnableMethodSecurity // annotation này cấu hình phân quyền trên method với hasRole, hasAuthority
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

        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable()) // disable csrf b?o v?

                // lu?ng auth cho truy c?p mà không c?n b?o v?
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/v1/auth/**").permitAll()
                        // .requestMatchers("/api/posts/create_post").permitAll()

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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
