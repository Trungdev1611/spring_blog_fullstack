package com.blog.blog.exception;

import lombok.Getter;

@Getter

public class ApiException extends RuntimeException {
    private String message;
    private int status = 0;
    private Object data = null;

    public ApiException(String message) {
        this.message = message;
    }
}
