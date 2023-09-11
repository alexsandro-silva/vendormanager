package br.com.arsilva.vendormanager.javaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Dados inválidos")
public class DadosInvalidosException extends RuntimeException {
    public DadosInvalidosException(String message) {
        super(message);
    }
}
