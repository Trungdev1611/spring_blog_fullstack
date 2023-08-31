package com.blog.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {

        // biến ex sẽ mang các đối số từ việc throw qua đây
        ErrorResponse errorData = new ErrorResponse(ex.getMessage(), ex.getStatus(), ex.getData());
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ErrorResponse> handleTokenException(TokenException ex) {

        // biến ex sẽ mang các đối số từ việc throw qua đây
        ErrorResponse errorData = new ErrorResponse(ex.getMessage(), ex.getStatus(), ex.getData());
        return new ResponseEntity<>(errorData, HttpStatus.UNAUTHORIZED);

    }

    // bắt khi không có quyền truy cập trong do phân quyền, spring sẽ tự động ném ra
    // lỗi AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                // ex.getMessage()
                "Không có quyền truy cập tài nguyên", 0, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
