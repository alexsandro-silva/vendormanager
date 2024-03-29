package br.com.arsilva.vendormanager.javaapi.service.impl;

import br.com.arsilva.vendormanager.javaapi.enumerations.TipoPessoa;
import br.com.arsilva.vendormanager.javaapi.exceptions.DadosInvalidosException;
import br.com.arsilva.vendormanager.javaapi.exceptions.FornecedorJaCadastradoException;
import br.com.arsilva.vendormanager.javaapi.exceptions.FornecedorNaoEncontradoException;
import br.com.arsilva.vendormanager.javaapi.exceptions.RecursoNaoEncontradoException;
import br.com.arsilva.vendormanager.javaapi.models.Endereco;
import br.com.arsilva.vendormanager.javaapi.models.Fornecedor;
import br.com.arsilva.vendormanager.javaapi.repository.EmpresaRepository;
import br.com.arsilva.vendormanager.javaapi.repository.FornecedorRepository;
import br.com.arsilva.vendormanager.javaapi.resource.dto.FornecedorDto;
import br.com.arsilva.vendormanager.javaapi.service.FornecedorService;
import br.com.arsilva.vendormanager.javaapi.utilities.Utilities;
import br.com.arsilva.vendormanager.javaapi.web.external.api.CepWebService;
import br.com.arsilva.vendormanager.javaapi.web.external.api.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Fornecedor cadastrarFornecedor(FornecedorDto fornecedorDto) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findByCpfCnpj(fornecedorDto.getCpfCnpj());
        if (fornecedor.isPresent()) {
            throw new FornecedorJaCadastradoException("Fornecedor já está cadastrado");
        }

        if (fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA) {
            AddressDto addressDto = CepWebService.getService().getAddressFromCep(fornecedorDto.getEndereco().getCep());

            if (addressDto.uf.equals("PR") && Utilities.isMenorIdade(fornecedorDto.getDataNascimento())) {
                throw new DadosInvalidosException("Fornecedor é menor de idade");
            }
        }

        boolean isValidDocument = (
                (fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA && Utilities.isValidCpf(fornecedorDto.getCpfCnpj()))
                || (fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_JURIDICA && Utilities.isValidCnpj(fornecedorDto.getCpfCnpj()))
        );

        if (!isValidDocument) {
            throw new DadosInvalidosException("Documento do fornecedor inválido");
        }

        Fornecedor newForn = Fornecedor.builder()
                .cpfCnpj(fornecedorDto.getCpfCnpj())
                .tipoPessoa(fornecedorDto.getTipoPessoa())
                .nome(fornecedorDto.getNome())
                .email(fornecedorDto.getEmail())
                .rg(fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA ? fornecedorDto.getRg() : "")
                .dataNascimento(fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA ? fornecedorDto.getDataNascimento() : null)
                .endereco(Endereco.builder()
                        .logradouro(fornecedorDto.getEndereco().getLogradouro())
                        .numero(fornecedorDto.getEndereco().getNumero())
                        .cep(fornecedorDto.getEndereco().getCep())
                        .bairro(fornecedorDto.getEndereco().getBairro())
                        .cidade(fornecedorDto.getEndereco().getCidade())
                        .uf(fornecedorDto.getEndereco().getUf())
                        .build()
                )
                .build();
        return fornecedorRepository.save(newForn);
    }

    @Override
    public Fornecedor adicionarFornecedor(String cnpjEmpresa, FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = empresaRepository.findByCnpj(cnpjEmpresa).map(empresa -> {
            Optional<Fornecedor> forn = fornecedorRepository.findByCpfCnpj(fornecedorDto.getCpfCnpj());
            if (forn.isPresent()) {
                empresa.adicionarFornecedor(forn.get());
                empresaRepository.save(empresa);
                return forn.get();
            }

            Fornecedor newForn = Fornecedor.builder()
                    .cpfCnpj(fornecedorDto.getCpfCnpj())
                    .tipoPessoa(fornecedorDto.getTipoPessoa())
                    .nome(fornecedorDto.getNome())
                    .email(fornecedorDto.getEmail())
                    .rg(fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA ? fornecedorDto.getRg() : "")
                    .dataNascimento(fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA ? fornecedorDto.getDataNascimento() : null)
                    .endereco(Endereco.builder()
                            .logradouro(fornecedorDto.getEndereco().getLogradouro())
                            .numero(fornecedorDto.getEndereco().getNumero())
                            .cep(fornecedorDto.getEndereco().getCep())
                            .bairro(fornecedorDto.getEndereco().getBairro())
                            .cidade(fornecedorDto.getEndereco().getCidade())
                            .uf(fornecedorDto.getEndereco().getUf())
                            .build()
                    )
                    .build();

            empresa.adicionarFornecedor(newForn);
            return fornecedorRepository.save(newForn);

        }).orElseThrow(() -> new RecursoNaoEncontradoException(String.format("Não foi encontrada empresa para o CNPJ: %", cnpjEmpresa)));

        return fornecedor;
    }

    @Override
    public Fornecedor buscarFornecedorPorCpfCnpj(String cpfCnpj) {
        Fornecedor fornecedor = fornecedorRepository.findByCpfCnpj(cpfCnpj.trim()).orElseThrow(() -> {
            return new FornecedorNaoEncontradoException("Fornecedor não encontrado");
        });

        return fornecedor;
    }

    @Override
    public void excluirFornecedor(String cpfCnpj) {
        Fornecedor fornecedor = fornecedorRepository.findByCpfCnpj(cpfCnpj.trim()).orElseThrow(() -> {
            return new FornecedorNaoEncontradoException("Fornecedor não encontrado");
        });

        fornecedorRepository.delete(fornecedor);
    }

    @Override
    public Fornecedor atualizarFornecedor(String cpfCnpj, FornecedorDto fornecedorDto) {
        fornecedorRepository.findByCpfCnpj(cpfCnpj.trim()).orElseThrow(() -> {
            return new FornecedorNaoEncontradoException("Fornecedor não encontrado");
        });

        if (fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA) {
            AddressDto addressDto = CepWebService.getService().getAddressFromCep(fornecedorDto.getEndereco().getCep());

            if (addressDto.uf.equals("PR") && Utilities.isMenorIdade(fornecedorDto.getDataNascimento())) {
                throw new DadosInvalidosException("Fornecedor é menor de idade");
            }
        }

        Fornecedor fornecedor = Fornecedor.builder()
                .cpfCnpj(fornecedorDto.getCpfCnpj())
                .tipoPessoa(fornecedorDto.getTipoPessoa())
                .nome(fornecedorDto.getNome())
                .email(fornecedorDto.getEmail())
                .rg(fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA ? fornecedorDto.getRg() : "")
                .dataNascimento(fornecedorDto.getTipoPessoa() == TipoPessoa.PESSOA_FISICA ? fornecedorDto.getDataNascimento() : null)
                .endereco(Endereco.builder()
                        .logradouro(fornecedorDto.getEndereco().getLogradouro())
                        .numero(fornecedorDto.getEndereco().getNumero())
                        .cep(fornecedorDto.getEndereco().getCep())
                        .bairro(fornecedorDto.getEndereco().getBairro())
                        .cidade(fornecedorDto.getEndereco().getCidade())
                        .uf(fornecedorDto.getEndereco().getUf())
                        .build()
                )
                .build();
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.findAll();
    }
}
