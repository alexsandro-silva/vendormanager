package br.com.arsilva.vendormanager.javaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Empresa já está cadastrada")
public class EmpresaJaCadastraException extends RuntimeException {

    public EmpresaJaCadastraException(String message) {
        super(message);
    }
}
