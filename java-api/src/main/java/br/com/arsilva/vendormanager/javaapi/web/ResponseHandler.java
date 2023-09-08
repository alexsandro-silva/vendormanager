package br.com.arsilva.vendormanager.javaapi.web;

import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
