package br.com.arsilva.vendormanager.javaapi.resource;

import br.com.arsilva.vendormanager.javaapi.models.Empresa;
import br.com.arsilva.vendormanager.javaapi.models.Fornecedor;
import br.com.arsilva.vendormanager.javaapi.resource.dto.EmpresaDto;
import br.com.arsilva.vendormanager.javaapi.resource.dto.FornecedorDto;
import br.com.arsilva.vendormanager.javaapi.service.EmpresaService;
import br.com.arsilva.vendormanager.javaapi.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendormanager/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public Empresa cadastrarEmpresa(@RequestBody EmpresaDto empresaDto) {
        return empresaService.cadastrarEmpresa(empresaDto);
    }

    @GetMapping("/cnpj/{cnpj}")
    public Empresa buscarEmpresa(@PathVariable("cnpj") String cnpj) {
        return empresaService.buscarEmpresaPorCnpj(cnpj);
    }

    @DeleteMapping("/cnpj/{cnpj}")
    public void excluirEmpresa(@PathVariable("cnpj") String cnpj) {
        empresaService.excluirEmpresa(cnpj);
    }

    @PatchMapping("/cnpj/{cnpj}")
    public Empresa atualizarEmpresa(@PathVariable("cnpj") String cnpj, @RequestBody EmpresaDto empresaDto) {
        return empresaService.atualizarEmpresa(cnpj, empresaDto);
    }

    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaService.listarEmpresas();
    }

    @PostMapping("/{cnpjEmpresa}/fornecedor")
    public Fornecedor adicionarFornecedor(@PathVariable(value = "cnpjEmpresa") String cnpjEmpresa, @RequestBody FornecedorDto fornecedorDto) {
        return fornecedorService.adicionarFornecedor(cnpjEmpresa, fornecedorDto);
    }

}
