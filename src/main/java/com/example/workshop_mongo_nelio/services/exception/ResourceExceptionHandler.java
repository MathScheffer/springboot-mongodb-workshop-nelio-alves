package com.example.workshop_mongo_nelio.services.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException ee, HttpServletRequest servletRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandarError e = new StandarError(System.currentTimeMillis(), status.value() , "Not Found", ee.getMessage(), servletRequest.getRequestURI());
        return ResponseEntity.status(status).body(e);
    }
}
