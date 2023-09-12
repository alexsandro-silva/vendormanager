package br.com.arsilva.vendormanager.javaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Fornecedor não encontrado")
public class FornecedorNaoEncontradoException extends RuntimeException {

    public FornecedorNaoEncontradoException(String message) {
        super(message);
    }
}
