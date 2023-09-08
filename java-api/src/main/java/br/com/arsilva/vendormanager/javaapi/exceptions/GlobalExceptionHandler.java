package br.com.arsilva.vendormanager.javaapi.exceptions;

import br.com.arsilva.vendormanager.javaapi.web.ApiError;
import br.com.arsilva.vendormanager.javaapi.web.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(RecursoNaoEncontradoException exception) {

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseHandler.generateResponse(apiError);

    }
}
