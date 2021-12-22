package com.murilonerdx.aondeta.exceptions.handler;

import com.murilonerdx.aondeta.exceptions.EmailNotFoundError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceGlobalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FieldExceptionError> handle(MethodArgumentNotValidException e){
        List<FieldExceptionError> list = new ArrayList<>();
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        errors.forEach(erro->{
            list.add(new FieldExceptionError(erro.getField(), erro.getDefaultMessage()));
        });
        return list;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<FieldExceptionError> handleBadRequestExceptions(Exception ex, WebRequest request) {
        FieldExceptionError exceptionResponse =
                new FieldExceptionError(
                        ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotFoundError.class)
    public final ResponseEntity<FieldExceptionError> handleEmailNotFoundException(RuntimeException ex, WebRequest request) {
        FieldExceptionError exceptionResponse =
                new FieldExceptionError(
                        ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<FieldExceptionError> handleCredenciaisInvalidasNotFoundException(BadCredentialsException ex, WebRequest request) {
        FieldExceptionError exceptionResponse =
                new FieldExceptionError(
                        ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<FieldExceptionError> handleEmailExistException(DataIntegrityViolationException ex, WebRequest request) {
        FieldExceptionError exceptionResponse =
                new FieldExceptionError(
                        ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
