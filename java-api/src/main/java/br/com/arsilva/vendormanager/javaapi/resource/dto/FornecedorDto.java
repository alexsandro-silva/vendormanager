package br.com.arsilva.vendormanager.javaapi.resource.dto;

import br.com.arsilva.vendormanager.javaapi.enumerations.TipoPessoa;
import br.com.arsilva.vendormanager.javaapi.models.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FornecedorDto {
    private String cpfCnpj;
    private TipoPessoa tipoPessoa;
    private String nome;
    private String email;
    private String rg;
    private Date dataNascimento;
    private Endereco endereco;
}
