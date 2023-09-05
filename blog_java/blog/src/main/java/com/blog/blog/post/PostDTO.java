package com.blog.blog.post;

import java.time.LocalDateTime;

import com.blog.blog.auth.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDTO {

    @NotBlank
    private String heading;

    private String avatar;

    @NotBlank
    private String content;
    private LocalDateTime dateCreated;
    private String authorName;
    private Long authorId;
    private User user;
}
