package com.blog.blog.requestDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class RequestCreatePostDTO {

    @NotBlank(message = "heading không được để trống hoặc null")
    private String heading;

    @NotBlank(message = "avatar không được để trống hoặc null")
    private String avatar;

    @NotBlank(message = "content không được để trống hoặc null")
    private String content;

}
