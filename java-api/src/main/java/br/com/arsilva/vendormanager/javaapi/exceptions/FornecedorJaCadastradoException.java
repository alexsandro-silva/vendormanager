package br.com.arsilva.vendormanager.javaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Fornecedor já cadastrado")
public class FornecedorJaCadastradoException extends RuntimeException {
    public FornecedorJaCadastradoException(String message) {
        super(message);
    }
}
