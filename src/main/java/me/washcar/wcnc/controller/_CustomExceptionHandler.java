package me.washcar.wcnc.controller;

import me.washcar.wcnc.exception.ErrorResponseDto;
import me.washcar.wcnc.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class _CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseDto> handleException(CustomException e) {
        return ErrorResponseDto.toResponseEntity(e.getErrorCode());
    }
}
