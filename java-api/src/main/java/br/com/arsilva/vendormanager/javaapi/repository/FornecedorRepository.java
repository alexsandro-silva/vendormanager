package br.com.arsilva.vendormanager.javaapi.repository;

import br.com.arsilva.vendormanager.javaapi.models.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {
    Optional<Fornecedor> findByCpfCnpj(String cpfCnpj);
}
