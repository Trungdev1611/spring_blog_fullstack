package com.blog.blog.exception;

import lombok.Getter;

@Getter

public class Apiexception extends RuntimeException {
    private String message;
    private int status = 0;
    private Object data = null;

    public Apiexception(String message) {
        this.message = message;
    }
}
