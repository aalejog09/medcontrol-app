package com.hmvss.auth.util.exceptions;


import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(ErrorDTO.builder()
                .code(APIError.VALIDATION_ERROR.getCode())
                .description(APIError.VALIDATION_ERROR.getHttpStatus().getReasonPhrase())
                .detail(errors)
                .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> notValids(Exception ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(APIError.MISSING_REQUEST_BODY.getMessage());


        return ResponseEntity.badRequest().body(ErrorDTO.builder()
                .code(APIError.MISSING_REQUEST_BODY.getCode())
                .description(APIError.MISSING_REQUEST_BODY.getHttpStatus().getReasonPhrase())
                .detail(errors)
                .build());
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> errorTaken(APIException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ex.setReasons(errors);

        return ResponseEntity.status(ex.getHttpStatus())
                .body(ErrorDTO.builder()
                        .code(ex.getCode()).description(ex.getHttpStatus().getReasonPhrase()).detail(ex.getReasons())
                        .build());
    }

}
