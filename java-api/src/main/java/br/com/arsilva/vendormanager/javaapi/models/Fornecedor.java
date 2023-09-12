package br.com.arsilva.vendormanager.javaapi.models;

import br.com.arsilva.vendormanager.javaapi.enumerations.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Fornecedor {
    @Id
    private String cpfCnpj;
    @Enumerated
    private TipoPessoa tipoPessoa;
    private String nome;
    private String email;
    private String rg;
    private Date dataNascimento;
    @Embedded
    private Endereco endereco;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Empresa empresa;
}
