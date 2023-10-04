package com.blog.blog.auth;

public interface AuthService {
    Object login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
