package com.blog.blog.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {
    private String tokenType = "Bearer";
    private String token;
}
