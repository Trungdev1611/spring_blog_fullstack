package com.blog.blog.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.jwt.JwtResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> postMethodName(@RequestBody LoginDTO loginDTO) {

        JwtResponse jwtResponse = new JwtResponse("Bearer", authService.login(loginDTO));
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> postMethodName(@RequestBody RegisterDTO registerDTO) {

        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }

}
