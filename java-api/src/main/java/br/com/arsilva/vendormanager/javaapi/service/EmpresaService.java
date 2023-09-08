package br.com.arsilva.vendormanager.javaapi.service;

import br.com.arsilva.vendormanager.javaapi.models.Empresa;
import br.com.arsilva.vendormanager.javaapi.resource.dto.EmpresaDto;

import java.util.List;

public interface EmpresaService {
    Empresa cadastrarEmpresa(EmpresaDto empresaDto);
    Empresa buscarEmpresaPorCnpj(Integer cnpj);
    void excluirEmpresa(Integer cnpj);
    Empresa atualizarEmpresa(Integer cnpj, EmpresaDto empresaDto);
    List<Empresa> listarEmpresas();
}
