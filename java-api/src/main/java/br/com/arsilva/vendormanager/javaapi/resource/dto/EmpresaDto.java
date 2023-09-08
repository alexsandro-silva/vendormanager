package br.com.arsilva.vendormanager.javaapi.resource.dto;

import br.com.arsilva.vendormanager.javaapi.models.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EmpresaDto {
    private Integer cnpj;
    private Integer numeroFilial;
    private Integer dv;
    private String nomeFantasia;
    private Endereco endereco;
}
