package br.com.arsilva.vendormanager.javaapi.resource;

import br.com.arsilva.vendormanager.javaapi.models.Fornecedor;
import br.com.arsilva.vendormanager.javaapi.resource.dto.EmpresaDto;
import br.com.arsilva.vendormanager.javaapi.resource.dto.FornecedorDto;
import br.com.arsilva.vendormanager.javaapi.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendormanager/api/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public Fornecedor cadastrarFornecedor(@RequestBody FornecedorDto fornecedorDto) {
        return fornecedorService.cadastrarFornecedor(fornecedorDto);
    }

    @GetMapping("/{cpfCnpj}")
    public Fornecedor buscarFornecedorPorCpfCnpj(@PathVariable("cpfCnpj") String cpfCnpj) {
        return fornecedorService.buscarFornecedorPorCpfCnpj(cpfCnpj);
    }

    @DeleteMapping("/{cpfCnpj}")
    public void excluirFornecedor(@PathVariable("cpfCnpj") String cpfCnpj) {
        fornecedorService.excluirFornecedor(cpfCnpj);
    }

    @PatchMapping("/{cpfCnpj}")
    public Fornecedor atualizarFornecedor(@PathVariable("cpfCnpj") String cpfCnpj, @RequestBody FornecedorDto fornecedorDto) {
        return fornecedorService.atualizarFornecedor(cpfCnpj, fornecedorDto);
    }

    @GetMapping
    public List<Fornecedor> listarFornecedores() {
        return fornecedorService.listarFornecedores();
    }

}
