package com.blog.blog.exception;

import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {
    private String message;
    private int status = 0;
    private Object data = null;

    public TokenException(String message) {
        this.message = message;
    }

}
