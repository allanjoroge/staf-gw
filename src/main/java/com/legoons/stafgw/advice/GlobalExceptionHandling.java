package com.legoons.stafgw.advice;

import com.legoons.stafgw.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNoTFound(){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleAllOthers(){
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
