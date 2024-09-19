package com.inditex.prices.infrastructure.input.rest.shared;

import com.inditex.prices.domain.exception.NotContentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    protected void handleNotContentException(NotContentException exc) {
        log.warn("NO CONTENT");
    }

}
