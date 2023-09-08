package br.com.arsilva.vendormanager.javaapi.service.impl;

import br.com.arsilva.vendormanager.javaapi.exceptions.RecursoNaoEncontradoException;
import br.com.arsilva.vendormanager.javaapi.models.Empresa;
import br.com.arsilva.vendormanager.javaapi.models.Endereco;
import br.com.arsilva.vendormanager.javaapi.repository.EmpresaRepository;
import br.com.arsilva.vendormanager.javaapi.resource.dto.EmpresaDto;
import br.com.arsilva.vendormanager.javaapi.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Empresa cadastrarEmpresa(EmpresaDto empresaDto) {
        Empresa empresa = buscarEmpresaPorCnpj(empresaDto.getCnpj());
        if (empresa != null) {
            throw new RuntimeException("Empresa já está cadastrada");
        }

        Empresa emp = Empresa.builder()
                .cnpj(empresaDto.getCnpj())
                .numeroFilial(empresaDto.getNumeroFilial())
                .dv(empresaDto.getDv())
                .nomeFantasia(empresaDto.getNomeFantasia())
                .endereco(Endereco.builder()
                        .tipoLogradouro(empresaDto.getEndereco().getTipoLogradouro())
                        .logradouro(empresaDto.getEndereco().getLogradouro())
                        .numero(empresaDto.getEndereco().getNumero())
                        .cep(empresaDto.getEndereco().getCep())
                        .cidade(empresaDto.getEndereco().getCidade())
                        .uf(empresaDto.getEndereco().getUf())
                        .build())
                .build();

        return empresaRepository.save(emp);
    }

    @Override
    public Empresa buscarEmpresaPorCnpj(Integer cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj).orElseThrow(() ->
            new RecursoNaoEncontradoException("Empresa com CNPJ :" + cnpj + " não encontrada")
        );

        return empresa;
    }

    @Override
    public void excluirEmpresa(Integer cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj).orElseThrow(() -> {
            return new RecursoNaoEncontradoException("Empresa não encontrada para o CNPJ " + cnpj);
        });

        empresaRepository.delete(empresa);
    }

    @Override
    public Empresa atualizarEmpresa(Integer cnpj, EmpresaDto empresaDto) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj).orElseThrow(() -> {
            return new RuntimeException("empresa não encontrada!");
        });

        Empresa emp = Empresa.builder()
                .nomeFantasia(empresaDto.getNomeFantasia())
                .endereco(Endereco.builder()
                        .tipoLogradouro(empresaDto.getEndereco().getTipoLogradouro())
                        .logradouro(empresaDto.getEndereco().getLogradouro())
                        .numero(empresaDto.getEndereco().getNumero())
                        .cep(empresaDto.getEndereco().getCep())
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
