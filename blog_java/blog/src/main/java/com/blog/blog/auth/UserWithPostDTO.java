package com.blog.blog.auth;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserWithPostDTO {
    Long id;

    String fullName;

    String email;

    String profilePicture;

    List<PostInUserProjection> posts;

}
