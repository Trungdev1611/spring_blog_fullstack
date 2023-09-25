package com.blog.blog.post;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.blog.blog.Comment.Comment;
import com.blog.blog.Comment.CommentProjectionPost;
import com.blog.blog.auth.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostDTOWithUserAndComment {
    Long id;

    String heading;

    String avatar;

    String content;

    LocalDateTime dateCreated;

    UserDTO user;

    // List<CommentProjectionPost> comments;

    // int countComment;

}
