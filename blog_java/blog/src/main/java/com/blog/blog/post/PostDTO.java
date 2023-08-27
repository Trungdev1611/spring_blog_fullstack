package com.blog.blog.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostDTO {
    private String heading;
    private String avatar;
    private String content;
    private LocalDateTime dateCreated;
}
