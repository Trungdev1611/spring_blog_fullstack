package com.blog.blog.Comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.Response.ResponseSuccess;
import com.blog.blog.exception.ResourceNotFoundEx;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/create/{idPost}")
    public ResponseEntity<ResponseSuccess> postMethodName(@RequestBody Comment comment, @PathVariable Long idPost) {
        ResponseSuccess response = new ResponseSuccess(commentService.createComment(comment, idPost));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<ResponseSuccess> getListCommentByIdPost(@PathVariable(required = false) Long postId) {
        ResponseSuccess response = new ResponseSuccess(commentService.getCommentByIdPost(postId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
