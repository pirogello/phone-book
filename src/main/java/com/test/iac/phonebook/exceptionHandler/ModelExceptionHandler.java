package com.test.iac.phonebook.exceptionHandler;

import com.test.iac.phonebook.exception.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ModelExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<String> handleModelNotFoundException(ModelNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
