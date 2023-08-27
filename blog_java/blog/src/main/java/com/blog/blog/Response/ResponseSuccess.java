package com.blog.blog.Response;

import lombok.Getter;

@Getter
public class ResponseSuccess {
    private String message = "success";
    private int status = 1;
    private Object data;

    public ResponseSuccess(Object data) {
        this.data = data;
    }

}
