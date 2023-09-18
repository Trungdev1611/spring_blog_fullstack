package com.blog.blog.post;

import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController
@RequestMapping("/api/v1/posts")
// @CrossOrigin(origins = "http://127.0.0.1:5173/", allowCredentials = "true")

@Tag(name = "Blog Post API")
public class PostController {

        private final PostService postService;

        @Autowired
        public PostController(PostService postService) {
                this.postService = postService;
        }

        @PreAuthorize("hasAnyRole('ADMIN','USER')") // khi không có quyền truy cập trong do phân quyền, spring sẽ tự
                                                    // động
                                                    // ném ra lỗi AccessDeniedException
        @PostMapping("/create_post")

        // chỉ định annotation để require token trên swagger
        @SecurityRequirement(name = "Bearer Authentication") // phải trùng với bên Configuration
        @Operation(summary = "CREAT POST REST API (ROLE USER ADMIN)", description = "create post rest api and save into database")

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "create post success", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTO.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

        public ResponseEntity<ResponseSuccess> createPost(@Valid @RequestBody PostDTO postDTO) {

                ResponseSuccess reponse = new ResponseSuccess(postService.createPost(postDTO));
                return new ResponseEntity<>(reponse, HttpStatus.CREATED);
        }

        @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @GetMapping("")

        @SecurityRequirement(name = "Bearer Authentication")
        @Operation(summary = "GET ALL POST (ROLE USER ADMIN)", description = "Get all post and info user create post")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get all post and user create post success", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProjectionPost.class))) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

        public ResponseEntity<ResponseSuccess> getAllPost() {
                ResponseSuccess reponse = new ResponseSuccess(postService.getAllPost());
                return new ResponseEntity<>(reponse, HttpStatus.OK);
        }

        // @PreAuthorize("hasAnyRole('USER','ADMIN')")
        // @GetMapping("/{id}")
        // public ResponseEntity<ResponseSuccess> getPostDetail(@PathVariable(name =
        // "id") Long idPost) {
        // ResponseSuccess reponse = new
        // ResponseSuccess(postService.getPostDetail(idPost));
        // return new ResponseEntity<>(reponse, HttpStatus.OK);
        // }

        @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @GetMapping("/{id}")

        @SecurityRequirement(name = "Bearer Authentication")
        @Operation(summary = "GET POST DETAIL AND USER CREATE POST BY ID POST (ROLE USER. ADMIN)", description = "Get post detail and info user create post by id post")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get post detail and info user create post by id post", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProjectionPost.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

        public ResponseEntity<ResponseSuccess> getPostDetailData(@PathVariable(name = "id") Long idPost) {
                ResponseSuccess reponse = new ResponseSuccess(postService.getPostDetail(idPost));
                return new ResponseEntity<>(reponse, HttpStatus.OK);
        }

        @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @GetMapping("/v2/{id}")

        @SecurityRequirement(name = "Bearer Authentication")
        @Operation(summary = "GET POST DETAIL, USER CREATE POST AND COMMENT LIST IN POST BY ID POST V2 (ROLE USER. ADMIN)", description = "GET POST DETAIL, USER CREATE POST AND COMMENT LIST IN POST BY ID POST V2")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "GET POST DETAIL, USER CREATE POST AND COMMENT LIST IN POST BY ID POST V2", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTOWithUserAndComment.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

        public ResponseEntity<ResponseSuccess> getPostDetailDataV2(@PathVariable(name = "id") Long idPost) {
                ResponseSuccess reponse = new ResponseSuccess(postService.getPostDetailV2(idPost));
                return new ResponseEntity<>(reponse, HttpStatus.OK);
        }

        // method delete và update chỉ cho phép ADMIN, và USER tạo post có quyền
        @PreAuthorize("(hasRole('ADMIN'))")
        @PutMapping("/{id}")

        @SecurityRequirement(name = "Bearer Authentication")
        @Operation(summary = "GET POST DETAIL, USER CREATE POST AND COMMENT LIST IN POST BY ID POST V2 (ROLE USER. ADMIN)", description = "GET POST DETAIL, USER CREATE POST AND COMMENT LIST IN POST BY ID POST V2")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "GET POST DETAIL, USER CREATE POST AND COMMENT LIST IN POST BY ID POST V2", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTOWithUserAndComment.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })

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