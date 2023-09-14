package com.blog.blog.post;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.blog.blog.Comment.Comment;
import com.blog.blog.Comment.CommentProjectionPost;

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

    HashMap<String, Object> user;

    List<CommentProjectionPost> comments;

    int countComment;

}
// interface UserProjection {
// Long getId();

// String getFull_name();

// String getEmail();

// String getProfile_picture();
// }