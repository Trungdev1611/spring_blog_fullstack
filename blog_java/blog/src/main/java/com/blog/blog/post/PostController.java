package com.blog.blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.Response.ResponseSuccess;
import com.blog.blog.security.CustomUserDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin(origins = "http://127.0.0.1:5173", allowCredentials = "true")

public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')") // khi không có quyền truy cập trong do phân quyền, spring sẽ tự động
                                                // ném ra lỗi AccessDeniedException
    @PostMapping("/create_post")
    public ResponseEntity<ResponseSuccess> createPost(@Valid @RequestBody PostDTO postDTO) {

        ResponseSuccess reponse = new ResponseSuccess(postService.createPost(postDTO));
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("")
    public ResponseEntity<ResponseSuccess> getAllPost() {
        ResponseSuccess reponse = new ResponseSuccess(postService.getAllPost());
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getPostDetail(@PathVariable(name = "id") Long idPost) {
        ResponseSuccess reponse = new ResponseSuccess(postService.getPostDetail(idPost));
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    // method delete và update chỉ cho phép ADMIN có quyền
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess> updatePost(@PathVariable(name = "id") Long idPost,
            @RequestBody PostDTO postDTO) {
        ResponseSuccess reponseUpdatePost = new ResponseSuccess(postService.updatePost(postDTO, idPost));
        return new ResponseEntity<>(reponseUpdatePost, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess> deletePost(@PathVariable(name = "id") Long idPost) {
        postService.deletePost(idPost);
        return new ResponseEntity<>(new ResponseSuccess(null), HttpStatus.OK);
    }
}