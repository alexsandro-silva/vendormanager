package br.com.arsilva.vendormanager.javaapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Empresa {
    @Id
    private String cnpj;
    private String nomeFantasia;
    @Embedded
    private Endereco endereco;
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
        })
    @JoinTable(name = "empresa_fornecedores",
            joinColumns = {@JoinColumn(name = "cnpj_empresa")},
            inverseJoinColumns = {@JoinColumn(name = "cnpj_fornecedor")}
    )
    private final List<Fornecedor> fornecedores = new ArrayList<>();

    public void adicionarFornecedor(Fornecedor fornecedor) {
        this.getFornecedores().add(fornecedor);
        fornecedor.getEmpresas().add(this);
    }
}
