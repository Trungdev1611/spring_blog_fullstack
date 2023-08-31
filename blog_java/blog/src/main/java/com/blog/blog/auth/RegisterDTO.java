package com.blog.blog.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "username không được để trống hoặc null")
    private String username;

    @NotBlank(message = "Password không được để trống hoặc null")
    private String password;

    @Email(message = "Email không đúng định dạng")
    private String email;

    @Size(max = 20, message = "Tên không được quá 20 ký tự")
    private String fullName;
}
