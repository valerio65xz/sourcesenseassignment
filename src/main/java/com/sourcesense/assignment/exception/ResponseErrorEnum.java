package com.sourcesense.assignment.exception;

public enum ResponseErrorEnum {

    BAD_REQUEST("Bad request", 400),
    BAD_JSON("Can't parse the JSON response", 400),
    BAD_SOURCE("The selected source has to be 'hackernews' or 'newyorktimes'", 400);

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