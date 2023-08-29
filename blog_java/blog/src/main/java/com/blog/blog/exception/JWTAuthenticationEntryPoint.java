package com.blog.blog.exception;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component

// authenticationEntryPoint là nơi bạn có thể tùy chỉnh hành vi khi xác thực
// thất bại và người dùng truy cập vào một tài nguyên yêu cầu xác thực
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                // authException.getMessage()
                "Lỗi trong entrypoin mẹ rồi"); // lỗi 401 và message
    }

}
