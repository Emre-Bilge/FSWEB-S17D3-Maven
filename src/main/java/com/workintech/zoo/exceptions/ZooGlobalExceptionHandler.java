package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException){
ZooErrorResponse errorResponse = new ZooErrorResponse( zooException.getHttpStatus().value(),
        zooException.getLocalizedMessage(),
        System.currentTimeMillis());
        log.error("exception occured" + zooException);
    return new ResponseEntity<>(errorResponse, zooException.getHttpStatus());
    }


    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception){
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getLocalizedMessage(),System.currentTimeMillis());
        log.error("exception occured" + exception);
        return new ResponseEntity<>(zooErrorResponse , HttpStatus.BAD_REQUEST);
    }

}
