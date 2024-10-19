package com.hmvss.api.util.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> errorTaken(BadRequestException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        return ResponseEntity.badRequest()
                .body(ErrorDTO.builder()
                        .code(APIError.VALIDATION_ERROR.getCode())
                        .description(APIError.VALIDATION_ERROR.getHttpStatus().getReasonPhrase())
                        .detail(errors)
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleBadRequestException(MethodArgumentTypeMismatchException ex) {
        List<String> errors = new ArrayList<>();
        log.info("ex: {}",ex.getName());
        errors.add(APIError.ARGUMENT_NOT_VALID.getMessage()+ex.getName());
        errors.add("Field: "+ex.getName()+", Value: "+ex.getValue());
        return ResponseEntity.badRequest().body(ErrorDTO.builder()
                .code(APIError.ARGUMENT_NOT_VALID.getCode())
                .description(APIError.ARGUMENT_NOT_VALID.getHttpStatus().getReasonPhrase())
                .detail(errors)
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        List<String> errors = new ArrayList<>();
        log.info("ex: {}", ex.getLocalizedMessage());

        errors.add("No es posible registrar la informacion - Violacion de integridad de datos");
        int detailIndex = ex.getLocalizedMessage().indexOf("Detail:");
        String error = "";
        if (detailIndex != -1) {
            int endIndex = ex.getLocalizedMessage().indexOf("]", detailIndex);
            if (endIndex != -1) {
                error =  ex.getLocalizedMessage().substring(detailIndex + 7, endIndex).trim();
            }

        }
        errors.add(error);
        return ResponseEntity.badRequest().body(ErrorDTO.builder()
                .code(APIError.DB_SAVING_ERROR.getCode())
                .description(APIError.DB_SAVING_ERROR.getHttpStatus().getReasonPhrase())
                .detail(errors)
                .build());
    }

}
