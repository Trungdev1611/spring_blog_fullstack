package com.blog.blog.ErrorDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseNotFound {
    private String message;
    private int status;
    private Object data;
}
