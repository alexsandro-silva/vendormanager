package br.com.arsilva.vendormanager.javaapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Empresa {
    @Id
    private Integer cnpj;
    private Integer numeroFilial;
    private Integer dv;
    private String nomeFantasia;
    @Embedded
    private Endereco endereco;
}
