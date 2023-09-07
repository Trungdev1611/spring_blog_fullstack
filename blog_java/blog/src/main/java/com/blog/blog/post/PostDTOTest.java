package com.blog.blog.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostDTOTest {

    private Long id;
    private String heading;
    private String avatar;

    private String content;

    private LocalDateTime dateCreated;
    private String full_name;
    private String email;

}
