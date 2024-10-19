package com.hmvss.api.util.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
public enum APIError {
    VALIDATION_ERROR("E0001",HttpStatus.BAD_REQUEST, "La informacion ingresada es incorrecta.", Collections.emptyList()),
    TOKEN_EXPIRED("E0002", HttpStatus.BAD_REQUEST, "Token expirado, debe ingresar nuevamente al sistema.", List.of("")),
    MISSING_REQUEST_BODY("E0003",HttpStatus.BAD_REQUEST, "Error procesando la solicitud, revise los datos.", Collections.emptyList()),
    ARGUMENT_NOT_VALID("E0004", HttpStatus.BAD_REQUEST, "El valor ingresado no es soportado para el campo: ", List.of("")),
    NOT_FOUND("E0005", HttpStatus.NOT_FOUND, "La informacion solicitada no se encuentra disponible.", List.of("")),
    DB_SAVING_ERROR("E0006", HttpStatus.CONFLICT , "No fue posible registrar la informacion por violacion de integridad de datos.", List.of("")),
    CITY_NOT_FOUND("E0007", HttpStatus.NOT_FOUND , "la ciudad solicitada no se encuentra disponible ", List.of("")),

    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
    private final List<String> reasons;

    APIError(String code, HttpStatus httpStatus, String message, List<String> reasons) {
        this.code = code;
        this.message = message;
        this.reasons = reasons;
        this.httpStatus = httpStatus;
    }


}

