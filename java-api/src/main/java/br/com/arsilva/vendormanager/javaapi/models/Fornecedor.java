package br.com.arsilva.vendormanager.javaapi.models;

import br.com.arsilva.vendormanager.javaapi.enumerations.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH
            },
            mappedBy = "fornecedores"
    )
    @JsonIgnore
    private final List<Empresa> empresas = new ArrayList<>();

    public void adicionarEmpresa(Empresa empresa) {
        this.getEmpresas().add(empresa);
        empresa.getFornecedores().add(this);
    }

    // remover da tabela de relacionamento
    @PreRemove
    private void removerRelacionamentoEmpresa() {
        for (Empresa empresa : this.empresas) {
            empresa.getFornecedores().remove(this);
        }
    }
}
