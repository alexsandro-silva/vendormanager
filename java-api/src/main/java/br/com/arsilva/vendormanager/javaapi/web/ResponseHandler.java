package br.com.arsilva.vendormanager.javaapi.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
