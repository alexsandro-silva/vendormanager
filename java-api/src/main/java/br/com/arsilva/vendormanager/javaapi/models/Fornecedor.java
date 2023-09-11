package br.com.arsilva.vendormanager.javaapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Fornecedor {
    @Id
    private String cpfCnpj;
    private String nome;
    private String email;
    private String rg;
    private Date dataNascimento;
    @Embedded
    private Endereco endereco;
}
