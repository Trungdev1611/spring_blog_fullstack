package com.blog.blog.auth;

import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.Response.ResponseSuccess;
import com.blog.blog.post.ProjectionPost;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/{id}")

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "GET INFO DETAILS USER AND LIST POST OF USER", description = "GET INFO DETAILS USER AND LIST POST OF USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get info and detail user and list post of user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserWithPostDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

    public ResponseEntity<ResponseSuccess> getMethodName(@PathVariable("id") Long idUser) {
        System.out.println("idUser::::" + idUser);

        ResponseSuccess reponse = new ResponseSuccess(userService.getUserDetailWithListPost(idUser));

        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "GET INFO DETAILS USER AND LIST POST OF USER V2", description = "GET INFO DETAILS USER AND LIST POST OF USER V2")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get info and detail user and list post of user v2", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserWithPostDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

    @GetMapping(value = "/user/v2/{id}")
    public ResponseEntity<ResponseSuccess> getMethodNameV2(@PathVariable("id") Long idUser) {
        System.out.println("idUser::::" + idUser);

        ResponseSuccess reponse = new ResponseSuccess(userService.getUserDetailWithListPostV2(idUser));

        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "GET INFO DETAILS USER ", description = "GET INFO DETAILS USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get info and detail user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserWithPostsProjection.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

    @GetMapping(value = "/userdetails/{id}")
    public ResponseEntity<ResponseSuccess> getDetailUser(@PathVariable("id") Long idUser) {
        System.out.println("idUser::::" + idUser);

        ResponseSuccess reponse = new ResponseSuccess(userService.getUserDetail(idUser));

        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

}
