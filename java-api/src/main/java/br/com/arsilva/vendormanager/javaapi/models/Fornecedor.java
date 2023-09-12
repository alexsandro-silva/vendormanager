package br.com.arsilva.vendormanager.javaapi.models;

import br.com.arsilva.vendormanager.javaapi.enumerations.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Fornecedor {
    @Id
    private String cpfCnpj;
    private TipoPessoa tipoPessoa;
    private String nome;
    private String email;
    private String rg;
    private Date dataNascimento;
    @Embedded
    private Endereco endereco;
    @ManyToOne(targetEntity = Empresa.class)
    private Empresa empresa;
}
