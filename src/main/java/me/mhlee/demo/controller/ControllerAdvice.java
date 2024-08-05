package me.mhlee.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private static final String DEFAULT_ERROR_MESSAGE = "알수없는 에러가 발생 했습니다.";

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationError(MethodArgumentNotValidException ex) {
        var message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleExceptionHandler(Exception ex) {
        return new ResponseEntity<>(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
