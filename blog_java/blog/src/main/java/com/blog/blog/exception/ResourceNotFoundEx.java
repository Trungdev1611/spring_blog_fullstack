package com.blog.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.NOT_FOUND) // mặc định status trả về cho exception
@Getter
@NoArgsConstructor
public class ResourceNotFoundEx extends RuntimeException {
    private String message = "Not Found";
    private int status = 0;
    private Object data = null;

    public ResourceNotFoundEx(String message) {
        this.message = message;
    }

}

/*
 * {
 * status: 'error',
 * data: null,
 * message: "Not Found"
 * }
 */
