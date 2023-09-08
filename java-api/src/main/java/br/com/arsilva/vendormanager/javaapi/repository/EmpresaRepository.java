package br.com.arsilva.vendormanager.javaapi.repository;

import br.com.arsilva.vendormanager.javaapi.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    Optional<Empresa> findByCnpj(Integer cnpj);
}
