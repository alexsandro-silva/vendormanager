package br.com.arsilva.vendormanager.javaapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class Endereco {
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String cep;
    private String cidade;
    private String uf;

}
