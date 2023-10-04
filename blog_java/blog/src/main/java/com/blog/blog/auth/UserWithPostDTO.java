package com.blog.blog.auth;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = false)

public class UserWithPostDTO extends UserDTO {
    List<PostInUserProjection> posts;

    public UserWithPostDTO(Long id, String full_name, String email, String profile_picture,
            List<PostInUserProjection> listPostWithUserId) {
        super(id, full_name, email, profile_picture);
        this.posts = listPostWithUserId;
    }

}
