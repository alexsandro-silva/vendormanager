package br.com.arsilva.vendormanager.javaapi.resource;

import br.com.arsilva.vendormanager.javaapi.models.Empresa;
import br.com.arsilva.vendormanager.javaapi.resource.dto.EmpresaDto;
import br.com.arsilva.vendormanager.javaapi.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendormanager/empresa")
public class EmpresaResource {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public Empresa cadastrarEmpresa(@RequestBody EmpresaDto empresaDto) {
        return empresaService.cadastrarEmpresa(empresaDto);
    }

    @GetMapping("/cnpj/{cnpj}")
    public Empresa buscarEmpresa(@PathVariable("cnpj") String cnpj) {
        Empresa empresa = empresaService.buscarEmpresaPorCnpj(cnpj);

        return empresa;
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

}
