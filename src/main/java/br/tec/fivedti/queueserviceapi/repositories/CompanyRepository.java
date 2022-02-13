package br.tec.fivedti.queueserviceapi.repositories;

import br.tec.fivedti.queueserviceapi.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}