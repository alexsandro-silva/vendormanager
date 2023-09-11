package br.com.arsilva.vendormanager.javaapi.service.impl;

import br.com.arsilva.vendormanager.javaapi.exceptions.DadosInvalidosException;
import br.com.arsilva.vendormanager.javaapi.exceptions.EmpresaJaCadastraException;
import br.com.arsilva.vendormanager.javaapi.exceptions.RecursoNaoEncontradoException;
import br.com.arsilva.vendormanager.javaapi.models.Empresa;
import br.com.arsilva.vendormanager.javaapi.models.Endereco;
import br.com.arsilva.vendormanager.javaapi.repository.EmpresaRepository;
import br.com.arsilva.vendormanager.javaapi.resource.dto.EmpresaDto;
import br.com.arsilva.vendormanager.javaapi.service.EmpresaService;
import br.com.arsilva.vendormanager.javaapi.utilities.ValidadorCNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Empresa cadastrarEmpresa(EmpresaDto empresaDto) {

        Optional<Empresa> empresa = empresaRepository.findByCnpj(empresaDto.getCnpj());
        if (empresa.isPresent()) {
            throw new EmpresaJaCadastraException("Empresa já está cadastrada");
        }

        boolean isCnpj = ValidadorCNPJ.validar(empresaDto.getCnpj());

        if (!isCnpj)
            throw new DadosInvalidosException("CNPJ inválido");

        Empresa emp = Empresa.builder()
                .cnpj(empresaDto.getCnpj())
                .nomeFantasia(empresaDto.getNomeFantasia())
                .endereco(Endereco.builder()
                        .tipoLogradouro(empresaDto.getEndereco().getTipoLogradouro())
                        .logradouro(empresaDto.getEndereco().getLogradouro())
                        .numero(empresaDto.getEndereco().getNumero())
                        .cep(empresaDto.getEndereco().getCep())
                        .bairro(empresaDto.getEndereco().getBairro())
                        .cidade(empresaDto.getEndereco().getCidade())
                        .uf(empresaDto.getEndereco().getUf())
                        .build())
                .build();

        return empresaRepository.save(emp);
    }

    @Override
    public Empresa buscarEmpresaPorCnpj(String cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj).orElseThrow(() ->
            new RecursoNaoEncontradoException("Empresa com CNPJ :" + cnpj + " não encontrada")
        );

        return empresa;
    }

    @Override
    public void excluirEmpresa(String cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj).orElseThrow(() -> {
            return new RecursoNaoEncontradoException("Empresa não encontrada para o CNPJ " + cnpj);
        });

        empresaRepository.delete(empresa);
    }

    @Override
    public Empresa atualizarEmpresa(String cnpj, EmpresaDto empresaDto) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj).orElseThrow(() -> {
            return new RecursoNaoEncontradoException("empresa não encontrada!");
        });

        Empresa emp = Empresa.builder()
                .cnpj(empresaDto.getCnpj())
                .nomeFantasia(empresaDto.getNomeFantasia())
                .endereco(Endereco.builder()
                        .tipoLogradouro(empresaDto.getEndereco().getTipoLogradouro())
                        .logradouro(empresaDto.getEndereco().getLogradouro())
                        .numero(empresaDto.getEndereco().getNumero())
                        .cep(empresaDto.getEndereco().getCep())
                        .bairro(empresaDto.getEndereco().getBairro())
                        .cidade(empresaDto.getEndereco().getCidade())
                        .uf(empresaDto.getEndereco().getUf())
                        .build())
                .build();

        return empresaRepository.save(emp);
    }

    @Override
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }
}
