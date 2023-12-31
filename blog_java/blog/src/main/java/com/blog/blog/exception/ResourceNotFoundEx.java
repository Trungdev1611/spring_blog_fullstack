package com.blog.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND) // mặc định status trả về cho exception
@Getter
public class ResourceNotFoundEx extends RuntimeException {
    private String message = "Not Found";
    private int status = 0;
    private Object data = null;

}

/*
 * {
 * status: 'error',
 * data: null,
 * message: "Not Found"
 * }
 */
