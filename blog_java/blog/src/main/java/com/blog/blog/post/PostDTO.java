package com.blog.blog.post;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostDTO {

    @NotBlank
    private String heading;

    private String avatar;

    @NotBlank
    private String content;
    private LocalDateTime dateCreated;
}
