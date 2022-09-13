package com.sourcesense.assignment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ResponseException ex) {
        return createErrorResponseEntity(
                ex.getError().getHttpStatus(),
                ex.getError().getMessage());
    }

    private ResponseEntity<ErrorResponse> createErrorResponseEntity(int status, String message){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(status);
        errorResponse.setMessage(message);

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }

}