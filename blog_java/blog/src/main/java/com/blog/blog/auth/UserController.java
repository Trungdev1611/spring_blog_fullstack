package com.blog.blog.auth;

import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<UserWithPostDTO> getMethodName(@PathVariable("id") Long idUser) {
        System.out.println("idUser::::" + idUser);
        UserWithPostDTO userDetail = userService.getUserDetail(idUser);
        return new ResponseEntity<>(userDetail, HttpStatus.OK);
    }

}
