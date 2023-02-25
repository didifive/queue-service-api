package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

    Empresa findByCpfCnpj(String cpfCnpj);

}