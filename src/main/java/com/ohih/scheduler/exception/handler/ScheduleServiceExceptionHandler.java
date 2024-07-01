package com.ohih.scheduler.exception.handler;

import com.ohih.scheduler.exception.customException.InsertFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ScheduleServiceExceptionHandler {

    @ExceptionHandler(InsertFailureException.class)
    public ResponseEntity<?> handleInstantiationException(InsertFailureException e) {
        System.out.println("ScheduleServiceExceptionHandler.handleInstantiationException");
        return new ResponseEntity<>("ERROR!", HttpStatus.BAD_REQUEST);
    }
}
