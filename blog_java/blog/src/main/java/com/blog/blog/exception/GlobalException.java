package com.blog.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.blog.blog.ErrorDTO.ErrorResponse;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundEx.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundEx(ResourceNotFoundEx ex) {

        // biến ex sẽ mang các đối số từ việc throw qua đây
        ErrorResponse errorData = new ErrorResponse(ex.getMessage(), ex.getStatus(), ex.getData());
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);

    }

    // lỗi login ở đây
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tài khoản hoặc mật khẩu không đúng");
    }

    @ExceptionHandler(Apiexception.class)
    public ResponseEntity<ErrorResponse> handleApiexceptionEx(Apiexception ex) {

        // biến ex sẽ mang các đối số từ việc throw qua đây
        ErrorResponse errorData = new ErrorResponse(ex.getMessage(), ex.getStatus(), ex.getData());
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);

    }
}
