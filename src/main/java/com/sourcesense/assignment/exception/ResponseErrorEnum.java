package com.sourcesense.assignment.exception;

public enum ResponseErrorEnum {

    BAD_REQUEST("Bad request", 400),
    BAD_JSON("Invalid JSON", 400);

    private final String message;
    private final int httpStatus;

    ResponseErrorEnum(String message, int httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

}