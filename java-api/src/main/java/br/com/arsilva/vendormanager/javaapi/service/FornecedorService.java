package br.com.arsilva.vendormanager.javaapi.service;

import br.com.arsilva.vendormanager.javaapi.models.Fornecedor;
import br.com.arsilva.vendormanager.javaapi.resource.dto.EmpresaDto;
import br.com.arsilva.vendormanager.javaapi.resource.dto.FornecedorDto;

import java.util.List;

public interface FornecedorService {
    Fornecedor cadastrarFornecedor(FornecedorDto fornecedorDto);
    Fornecedor buscarFornecedorPorCpfCnpj(String cpfCnpj);
    void excluirFornecedor(String cpfCnpj);
    Fornecedor atualizarFornecedor(String cpfCnpj, FornecedorDto fornecedorDto);
    List<Fornecedor> listarFornecedores();
}
