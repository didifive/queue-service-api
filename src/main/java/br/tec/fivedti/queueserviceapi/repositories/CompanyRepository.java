package br.tec.fivedti.queueserviceapi.repositories;

import br.tec.fivedti.queueserviceapi.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Company findByCnpj(String cnpj);

}