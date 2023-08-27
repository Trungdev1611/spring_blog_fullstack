package com.blog.blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.Response.ResponseSuccess;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://127.0.0.1:5173/", allowCredentials = "true")

public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create_post")
    public ResponseEntity<ResponseSuccess> createPost(@RequestBody PostDTO postDTO) {
        ResponseSuccess reponse = new ResponseSuccess(postService.createPost(postDTO));
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ResponseSuccess> getAllPost() {
        ResponseSuccess reponse = new ResponseSuccess(postService.getAllPost());
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getPostDetail(@PathVariable(name = "id") Long idPost) {
        ResponseSuccess reponse = new ResponseSuccess(postService.getPostDetail(idPost));
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }
}
