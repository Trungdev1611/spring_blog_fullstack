package com.blog.blog.Comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.Response.ResponseSuccess;
import com.blog.blog.post.PostDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "Comments in PostID")
public class CommentController {

    private CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/create/{idPost}")

    @SecurityRequirement(name = "Bearer Authentication") // phải trùng với bên Configuration
    @Operation(summary = "CREATE COMMENT IN POST", description = "CREATE COMMENT IN POST")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "create comment success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

    public ResponseEntity<ResponseSuccess> postMethodName(@RequestBody Comment comment, @PathVariable Long idPost) {
        ResponseSuccess response = new ResponseSuccess(commentService.createComment(comment, idPost));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/post/{postId}")

    @SecurityRequirement(name = "Bearer Authentication") // phải trùng với bên Configuration
    @Operation(summary = "GET LIST COMMENT IN POST WITH POST ID", description = "GET LIST COMMENT IN POST WITH POST ID")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get list comment success", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CommentProjectionPost.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

    public ResponseEntity<ResponseSuccess> getListCommentByIdPost(@PathVariable(required = false) Long postId,
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        ResponseSuccess response = new ResponseSuccess(commentService.getCommentByIdPost(postId, pageIndex, pageSize));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
