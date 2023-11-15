package br.com.cleyton.cadastraProdutos.exception.builder;

import br.com.cleyton.apiProdutos.ApiProdutosTests.exception.*;
import br.com.cleyton.apiProdutos.exception.*;
import br.com.cleyton.cadastraProdutos.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    public ResponseEntity<Object> productAlreadyExistsException(EntityAlreadyExistsException e) {
        HttpStatus conflict = HttpStatus.CONFLICT;

        ApiException apiException = new ApiException(
                conflict,
                e.getMessage(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, conflict);
    }

    @ExceptionHandler(value = {MissingTermsException.class})
    public ResponseEntity<Object> termsMissingException(MissingTermsException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                badRequest,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                notFound,
                e.getMessage(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {EmptyPageException.class})
    public ResponseEntity<Object> emptyPageException(EmptyPageException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                badRequest,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {EmptyRequestBodyException.class})
    public ResponseEntity<Object> emptyRequestBodyException(EmptyRequestBodyException e) {
        HttpStatus conflict = HttpStatus.CONFLICT;

        ApiException apiException = new ApiException(
                conflict,
                e.getMessage(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, conflict);
    }
}
