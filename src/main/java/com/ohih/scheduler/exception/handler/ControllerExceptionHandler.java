package com.ohih.scheduler.exception.handler;

import com.ohih.scheduler.exception.customException.InsertFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({InsertFailureException.class})
    public String handleInsertFailureException(InsertFailureException e) {
        System.out.println("ControllerExceptionHandler.handleInsertFailureException");
        return "test";
    }
}
