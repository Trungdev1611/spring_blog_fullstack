package com.blog.blog.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.Response.ResponseSuccess;
import com.blog.blog.jwt.JwtResponse;
import com.blog.blog.post.PostDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/v1/auth")
// @CrossOrigin(origins = "http://127.0.0.1:5173/", allowCredentials = "true")
@Tag(name = "Authentication API")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "LOGIN USER AND GET TOKEN", description = "LOGIN USER AND GET TOKEN")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "login user and get token", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccess.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

    public ResponseEntity<ResponseSuccess> postMethodName(@Valid @RequestBody LoginDTO loginDTO) {
        logger.info("Loggger1 trong login");
        ResponseSuccess responseSuccess = new ResponseSuccess(authService.login(loginDTO));
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    @Operation(summary = "CREATE USER IN APP (ROLE USER)", description = "CREATE USER IN APP (ROLE USER)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create user in app", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccess.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

    public ResponseEntity<ResponseSuccess> postMethodName(@Valid @RequestBody RegisterDTO registerDTO) {
        logger.info("Loggger2 trong register");
        ResponseSuccess responseSuccess = new ResponseSuccess(authService.register(registerDTO));
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

}
