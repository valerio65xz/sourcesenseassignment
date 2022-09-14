package com.sourcesense.assignment.exception;

import com.sourcesense.assignment.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalControllerExceptionHandlerTest extends BaseUnitTest {

    private final GlobalControllerExceptionHandler handler = new GlobalControllerExceptionHandler();

    @Test
    void handleException_badRequest() {
        ResponseException ex = new ResponseException(ResponseErrorEnum.BAD_REQUEST);

        ResponseEntity<ErrorResponse> result = handler.handleException(ex);

        assertEquals(ex.getError().getHttpStatus(), result.getBody().getStatus());
        assertEquals(ex.getError().getMessage(), result.getBody().getMessage());
    }

}