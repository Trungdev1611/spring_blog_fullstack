package com.blog.blog.post;

import java.time.LocalDateTime;

import com.blog.blog.auth.User;

import io.swagger.v3.oas.annotations.Hidden;
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

    private Long Id;
    @NotBlank(message = "heading không được để trống hoặc null")
    private String heading;

    @NotBlank(message = "avatar không được để trống hoặc null")
    private String avatar;

    @NotBlank(message = "content không được để trống hoặc null")
    private String content;

    @Hidden // sử dụng hidden để ẩn các trường trên swagger docs
    private LocalDateTime dateCreated;
    @Hidden // sử dụng hidden để ẩn các trường trên swagger docs
    private String authorName;
    @Hidden // sử dụng hidden để ẩn các trường trên swagger docs
    private Long authorId;
    @Hidden // sử dụng hidden để ẩn các trường trên swagger docs
    private User user;
}
