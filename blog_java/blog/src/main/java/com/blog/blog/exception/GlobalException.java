package com.blog.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.blog.blog.ErrorDTO.ErrorResponseNotFound;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundEx.class)
    public ResponseEntity<ErrorResponseNotFound> handleResourceNotFoundEx(ResourceNotFoundEx ex) {

        // biến ex sẽ mang các đối số từ việc throw qua đây
        ErrorResponseNotFound errorData = new ErrorResponseNotFound(ex.getMessage(), ex.getStatus(), ex.getData());
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);
    }
}
