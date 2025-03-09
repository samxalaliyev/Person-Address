package com.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PersonException.class)
    public ExceptionModel handlePesonException(PersonException exception){
        return getExceptionModel(exception.getMessage());
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ExceptionModel handleException(Exception exception){
        return getExceptionModel("Something went wrong");
    }

    private static ExceptionModel getExceptionModel(String exception) {
        return ExceptionModel.builder()
                .statusCode(400)
                .message(exception)
                .build();
    }
}
