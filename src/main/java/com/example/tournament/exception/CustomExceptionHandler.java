package com.example.tournament.exception;

import com.example.tournament.constant.SourceTypes;
import com.example.tournament.model.response.serviceResponse.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler({NoDataFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceResponse notFoundException(NoDataFoundException ex) {
        log.error("exception : " + ex);
        ServiceResponse response = ServiceResponse.createErrorResponse(HttpStatus.NOT_FOUND.value(), SourceTypes.SYSTEM.name(), ex.getMessage());
        log.error(response.toString());
        return response;
    }

}
